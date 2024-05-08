use std::fs::File;
use std::io::{self, BufRead};
use std::rc::Rc;

fn substitute(input: String, pattern: &str, replacement: char) -> String {
    let mut output = String::new();
    let mut start = 0;
    while let Some(pos) = input[start..].find(pattern) {
        output.push_str(&input[start..start + pos]);
        output.push(replacement);
        start += pos + pattern.len();
    }
    output.push_str(&input[start..]);
    output
}

pub fn substitute_all(input: String) -> String {
    let substitutions = [
        ("Sxy(Sxy(Kx(Q), Sxy(Sxy(Kx(Q), Sxy(I, Kx(S))), Sxy(Sxy(Kx(Q), Sxy(I, Kx(Q))), Sxy(Sxy(Kx(Q), Sxy(I, Kx(Zero))), Sxy(I, Kx(One)))))), Sxy(I, Kx(K)))", 'L'),
        ("Sxy(Kx(Sx(Kx(K))), Sxy(Kx(S), Sxy(Kx(K), Sxy(I, Kx(K)))))", 'i'),
        ("Sxy(Kx(K), Sxy(Sxy(Kx(S), Sxy(Kx(Sx(Kx(S))), Sxy(Sxy(Kx(S), Sxy(Kx(K), Sxy(Kx(S), Sxy(Kx(Sx(Sxy(Sxy(I, Kx(i)), Kx(Kx(K))))), K)))), Kx(K)))), Kx(SK)))", 'C'),
        ("Sxy(Sxy(Kx(Q), Sxy(Kx(C), SII)), Sxy(Kx(L), Sxy(SII, Kx(K))))", 'm'),
        ("SIIx(m)", 'M'),
    ];
    let mut output = input;
    for (pattern, replacement) in substitutions {
        output = substitute(output, pattern, replacement);
    }
    output
}

#[derive(Debug)]
pub enum Node {
    K,
    Kx(Rc<Node>),
    S,
    Sx(Rc<Node>),
    Sxy(Rc<Node>, Rc<Node>),
    Sxyz(Rc<Node>, Rc<Node>, Rc<Node>),
    SK,
    I,
    SI,
    SII,
    SIIx(Rc<Node>),
    Q,
    Qx(Rc<Node>),
    Qxy(Rc<Node>, Rc<Node>),
    Zero,
    ZeroX(Rc<Node>),
    One,
    OneX(Rc<Node>),
    Apply(Rc<Node>, Rc<Node>),
}

impl Node {
    pub fn rc_apply(rc_self: Rc<Node>, other: Rc<Node>) -> Rc<Node> {
        match &*rc_self {
            Node::K => match &*other {
                Node::I => Rc::new(Node::SK),
                _ => Rc::new(Node::Kx(Rc::clone(&other))),
            },
            Node::Kx(x) => Rc::clone(&x),
            Node::S => match &*other {
                Node::K => Rc::new(Node::SK),
                Node::I => Rc::new(Node::SI),
                _ => Rc::new(Node::Sx(Rc::clone(&other))),
            },
            Node::Sx(x) => Rc::new(Node::Sxy(Rc::clone(&x), Rc::clone(&other))),
            Node::Sxy(x, y) => Rc::new(Node::Sxyz(Rc::clone(&x), Rc::clone(&y), Rc::clone(&other))),
            Node::SK => Rc::new(Node::I),
            Node::I => Rc::clone(&other),
            Node::SI => match &*other {
                Node::I => Rc::new(Node::SII),
                _ => Rc::new(Node::Sxy(Rc::new(Node::I), Rc::clone(&other))),
            },
            Node::SII => Rc::new(Node::SIIx(Rc::clone(&other))),
            Node::Q => Rc::new(Node::Qx(Rc::clone(&other))),
            Node::Qx(x) => Rc::new(Node::Qxy(Rc::clone(&x), Rc::clone(&other))),
            Node::Qxy(x, y) => Rc::new(Node::Qxy(Node::rc_apply(Rc::clone(&x), Rc::clone(&other)), Node::rc_apply(Rc::clone(&y), Rc::clone(&other)))),
            Node::Zero => Rc::new(Node::ZeroX(Rc::clone(&other))),
            Node::ZeroX(x) => Rc::new(Node::ZeroX(Node::rc_apply(Rc::clone(&x), Rc::clone(&other)))),
            Node::One => Rc::new(Node::OneX(Rc::clone(&other))),
            Node::OneX(x) => Rc::new(Node::OneX(Node::rc_apply(Rc::clone(&x), Rc::clone(&other)))),
            _ => Rc::new(Node::Apply(Rc::clone(&rc_self), Rc::clone(&other))),
        }
    }

    pub fn one_step_normalize(rc_self: Rc<Node>) -> Option<Rc<Node>> {
        let result = match &*rc_self {
            Node::Sxyz(x, y, z) => Some(Node::rc_apply(Node::rc_apply(Rc::clone(&x), Rc::clone(&z)), Node::rc_apply(Rc::clone(&y), Rc::clone(&z)))),
            Node::SIIx(x) => Some(Node::rc_apply(Rc::clone(&x), Rc::clone(&x))),
            Node::Apply(f, x) => match Node::one_step_normalize(Rc::clone(&f)) {
                Some(nf) => Some(Node::rc_apply(nf, Rc::clone(&x))),
                _ => None,
            }
            _ => None,
        };
        result
    }

    fn from_str(s: &str) -> Result<Rc<Node>, ()> {
        let mut chars = s.chars();
        let node = parse_node(&mut chars, 0)?;
        let next_char = chars.next();
        if next_char.is_some() {
            panic!("Here")
        } else {
            Ok(node)
        }
    }
}

fn parse_node(chars: &mut std::str::Chars, level: u8) -> Result<Rc<Node>, ()> {
    let result = match chars.next() {
        Some('1') => {
            match chars.next() {
                Some('1') => Ok(Rc::new(Node::K)),
                Some('0') => {
                    match chars.next() {
                        Some('0') => Ok(Rc::new(Node::S)),
                        Some('1') => {
                            match chars.next() {
                                Some('0') => Ok(Rc::new(Node::Q)),
                                Some('1') => {
                                    match chars.next() {
                                        Some('0') => Ok(Rc::new(Node::Zero)),
                                        Some('1') => Ok(Rc::new(Node::One)),
                                        _ => panic!("Here"),
                                    }
                                }
                                _ => panic!("Here"),
                            }
                        }
                        _ => panic!("Here"),
                    }
                }
                _ => panic!("Here"),
            }
        }
        Some('0') => {
            let new_level = level + 1;
            let a = parse_node(chars, new_level)?;
            let b = parse_node(chars, new_level)?;
            Ok(Node::rc_apply(a, b))
        }
        _ => panic!("Here"),
    };
    result
}

const LOADER: &str = "000010000100111100100111100100001000111010001000110010001111001000010001110000100011010001110000100001000111000010001111001000111000010001101000010000100001001111011001000110100011110010001110000100011110010000100111101111011011111101111011010011001000010011110010011110010001100100001000111010001000010001110100010000100111101110000100001000111010001000010011110111010001000010001110100010000100111101110110001000010011110111011100100001001111011110010000100001001111001001111011110110010011100";

pub fn get_loader_node() -> Rc<Node> {
    Node::from_str(LOADER).unwrap()
}

pub struct BinaryVec(Vec<bool>);

pub fn txt(vec: &Vec<bool>) -> String {
    vec.iter().map(|b| {if *b {"1"} else {"0"}}).collect::<Vec<_>>().join("")
}

pub fn read_txt(input_file_name: &str) -> Vec<bool> {
    let input_file = File::open(input_file_name).unwrap();
    let mut result = Vec::new();
    for line in io::BufReader::new(input_file).lines() {
        let line = line.unwrap();
        for c in line.chars() {
            match c {
                '0' => result.push(false),
                '1' => result.push(true),
                _ => (),
            }
        }
    }
    result
}

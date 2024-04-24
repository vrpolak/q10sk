use std::env;
use std::fs::File;
use std::io::{self, BufRead, Write};
use std::rc::Rc;
//use std::str::FromStr;

// i
//const LOADER: &str = "0010000100011100001000111100100011100001000110100011100001000110100011110010000100111100100111101111";
// C
//const LOADER: &str = "0010001111001000010001110000100011010001110000100001000111000010001111001000111000010001101000010011111101111011010011";
// L
//const LOADER: &str = "0010000100011101000100001000111010001000010011110111000010000100011101000100001001111011101000100001000111010001000010011110111011000100001001111011101110010000100111101111";
// m
//const LOADER: &str = "00100001000111010001000111100100001000111000010001101000111000010000100011100001000111100100011100001000110100001001111110111101101001100100011001000010001110100010000100011101000100001001111011100001000010001110100010000100111101110100010000100011101000100001001111011101100010000100111101110111001000010011110111100100001000010011110010011110110010000100011100001000111100100011100001000110100011100001000110100011110010000100111100100111101111";
// P
const LOADER: &str = "0000100001001111001001111001000010001110100010001111001000010001110000100011010001110000100001000111000010001111001000111000010001101000010011111101111011010011001000110010000100011101000100001000111010001000010011110111000010000100011101000100001001111011101000100001000111010001000010011110111011000100001001111011101110010000100111101111001000010000100111100100111101100100001000111000010001111001000111000010001101000111000010001101000111100100001001111001001111011110110010011100";

fn substitute_i(input: String) -> String {
    let pattern = "Sxy(Sxy(Kx(S), Sxy(Kx(K), Sxy(Kx(S), Sxy(Kx(Sx(Kx(S))), Sxy(Kx(Sx(Kx(K))), Sxy(Sxy(K, K), Sxy(K, K))))))), Kx(K))";
    let mut output = String::new();
    let mut start = 0;

    while let Some(pos) = input[start..].find(pattern) {
        output.push_str(&input[start..start + pos]);
        output.push('i');
        start += pos + pattern.len();
    }

    output.push_str(&input[start..]);
    output
}

fn substitute_c(input: String) -> String {
    let pattern = "Sxy(Kx(K), Sxy(Sxy(Kx(S), Sxy(Kx(Sx(Kx(S))), Sxy(Sxy(Kx(S), Sxy(Kx(K), Sxy(Kx(S), Sxy(Kx(Sx(Sxy(K, K))), K)))), Kx(K)))), Kx(Sx(K))))";
    let mut output = String::new();
    let mut start = 0;

    while let Some(pos) = input[start..].find(pattern) {
        output.push_str(&input[start..start + pos]);
        output.push('C');
        start += pos + pattern.len();
    }

    output.push_str(&input[start..]);
    output
}

fn substitute_l(input: String) -> String {
    let pattern = "Sxy(Sxy(Kx(Q), Sxy(Sxy(Kx(Q), Sxy(Sxy(K, K), Kx(S))), Sxy(Sxy(Kx(Q), Sxy(Sxy(K, K), Kx(Q))), Sxy(Sxy(Kx(Q), Sxy(Sxy(K, K), Kx(Zero))), Sxy(Sxy(K, K), Kx(One)))))), Sxy(Sxy(K, K), Kx(K)))";
    let mut output = String::new();
    let mut start = 0;

    while let Some(pos) = input[start..].find(pattern) {
        output.push_str(&input[start..start + pos]);
        output.push('L');
        start += pos + pattern.len();
    }

    output.push_str(&input[start..]);
    output
}

fn substitute_m(input: String) -> String {
    let pattern = "Sxy(Sxy(Kx(Q), C), Sxy(Kx(L), Sxy(Sxy(Sxy(K, K), Sxy(K, K)), Kx(i))))";
    let mut output = String::new();
    let mut start = 0;

    while let Some(pos) = input[start..].find(pattern) {
        output.push_str(&input[start..start + pos]);
        output.push('m');
        start += pos + pattern.len();
    }

    output.push_str(&input[start..]);
    output
}

fn substitute_all(input: String) -> String {
    substitute_m(substitute_l(substitute_c(substitute_i(input))))
}

#[derive(Debug)]
enum Node {
    K,
    Kx(Rc<Node>),
    S,
    Sx(Rc<Node>),
    Sxy(Rc<Node>, Rc<Node>),
    Sxyz(Rc<Node>, Rc<Node>, Rc<Node>),
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
    fn rc_apply(rc_self: Rc<Node>, other: Rc<Node>) -> Rc<Node> {
        // This also performs half-lazy evaluation.
        match &*rc_self {
            Node::K => Rc::new(Node::Kx(Rc::clone(&other))),
            Node::Kx(x) => Rc::clone(&x),
            Node::S => Rc::new(Node::Sx(Rc::clone(&other))),
            Node::Sx(x) => Rc::new(Node::Sxy(Rc::clone(&x), Rc::clone(&other))),
            Node::Sxy(x, y) => Rc::new(Node::Sxyz(Rc::clone(&x), Rc::clone(&y), Rc::clone(&other))),
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

    fn one_step_normalize(rc_self: Rc<Node>) -> Option<Rc<Node>> {
//        println!("starting normalization of {:?}", &rc_self);
        let result = match &*rc_self {
            // This performs one step towards full normalization, but no I/O.
            Node::Sxyz(x, y, z) => Some(Node::rc_apply(Node::rc_apply(Rc::clone(&x), Rc::clone(&z)), Node::rc_apply(Rc::clone(&y), Rc::clone(&z)))),
            Node::Apply(f, x) => match Node::one_step_normalize(Rc::clone(&f)) {
                Some(nf) => Some(Node::rc_apply(nf, Rc::clone(&x))),
                _ => None,
            }
            _ => None,
        };
//        println!("normalization ended with {:?}", &result);
        result
    }

    fn from_str(s: &str) -> Result<Rc<Node>, ()> {
        let mut chars = s.chars();
        let node = parse_node(&mut chars, 0)?;
        let next_char = chars.next();
        if next_char.is_some() {
            println!("Next char {:?}", next_char);
            panic!("Here")
        }
        Ok(node)
    }
}

//impl FromStr for Node {
//    type Err = ();
//
//    fn from_str(s: &str) -> Result<Self, Self::Err> {
//        let mut chars = s.chars();
//        let node = parse_node(&mut chars)?;
//        if chars.next().is_some() {
//            panic!("Here")
//        } else {
//            Ok(*node)
//        }
//    }
//}

fn parse_node(chars: &mut std::str::Chars, level: u8) -> Result<Rc<Node>, ()> {
    let result = match chars.next() {
        Some('1') => {
//            println!("Read 1");
            match chars.next() {
                Some('1') => {
//                    println!("Read 1: K");
                    Ok(Rc::new(Node::K))
                },
                Some('0') => {
//                    println!("Read 0");
                    match chars.next() {
                        Some('0') => {
//                            println!("Read 0: S");
                            Ok(Rc::new(Node::S))
                        },
                        Some('1') => {
//                            println!("Read 1");
                            match chars.next() {
                                Some('0') => {
//                                    println!("Read 0: Q");
                                    Ok(Rc::new(Node::Q))
                                },
                                Some('1') => {
//                                    println!("Read 1");
                                    match chars.next() {
                                        Some('0') => {
//                                            println!("Read 0: Zero");
                                            Ok(Rc::new(Node::Zero))
                                        },
                                        Some('1') => {
//                                            println!("Read 1: One");
                                            Ok(Rc::new(Node::One))
                                        },
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
//            println!("Read 0: ` and entering level {}", new_level);
            let a = parse_node(chars, new_level)?;
//            println!("Still need to read argument for level {}", new_level);
            let b = parse_node(chars, new_level)?;
            Ok(Node::rc_apply(a, b))
        }
        _ => panic!("Here"),
    };
//    println!("closing level {} with state {:?}", level, result);
    result
}

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() != 4 {
        eprintln!("Usage: {} <input_file> <output_file> <max_steps>", args[0]);
        return;
    }

    let input_file = File::open(&args[1]).unwrap();
    let mut output_file = File::create(&args[2]).unwrap();

    let max_steps: usize = args[3].parse().unwrap();

    let mut state = Node::from_str(LOADER).unwrap();

    let mut input_bits = Vec::new();
    for line in io::BufReader::new(input_file).lines() {
        let line = line.unwrap();
        for c in line.chars() {
            match c {
                '0' => input_bits.push(false),
                '1' => input_bits.push(true),
                _ => (),
            }
        }
    }
    println!("Input bits: {:?}", input_bits);
    let mut input_iter = input_bits.iter();

    let mut output_bits = Vec::new();

    println!("starting state {:?}", &substitute_all(format!("{:?}", &state)));
    for _ in 0..max_steps {
        let maybe_new_state = Node::one_step_normalize(Rc::clone(&state));
        if maybe_new_state.is_some() {
            state = maybe_new_state.unwrap();
            println!("new state no io: {:?}\n", &substitute_all(format!("{:?}", &state)));
            continue
        }
        println!("old state is io or halt.");
        state = match &*state {
            Node::ZeroX(x) => {
                output_bits.push(false);
                Rc::clone(&x)
            },
            Node::OneX(x) => {
                output_bits.push(true);
                Rc::clone(&x)
            },
            Node::Qxy(x, y) => {
                match input_iter.next() {
                    Some(false) => {
                        println!("Next input bit is 0, choosing first.");
                        Rc::clone(&x)
                    },
                    Some(true) => {
                        println!("Next input bit is 1, choosing second.");
                        Rc::clone(&y)
                    },
                    _ => panic!("End of input."),
                }
            },
            _ => {
                panic!("Halted")
            },
        };
        println!("new state after io: {:?}", &substitute_all(format!("{:?}", &state)));
    }
    println!("iteration limit reached.");

    for i in 0..output_bits.len() {
        writeln!(
            output_file,
            "{}",
            match output_bits[i] {
                false => '0',
                true => '1',
            },
        ).unwrap();
    }
}

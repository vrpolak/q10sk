use std::env;
use std::fs::File;
use std::io::{self, BufRead, Write};
use std::rc::Rc;
use std::str::FromStr;

// canon const: LOADER: &str = "100111000011111000011111000010000111101000010011010000111101111100001000010111110111110000100001011011011111000010000101011010000100001010110111110000100001010110100001000010011011111000010000101011010000100001010110100001000011010000111111100001000110100001101010011010000111110000111110000100000";
// stupid inverse: const LOADER: &str = "000001000011111000011111000010110010101100001011000100001111111000010110000100001011010100001000010110101000010000111110110010000100001011010100001000011111011010100001000010110101000010000111110110110100001000011111011111010000100001111101111000010110010000101111000010000111110000111110000111001";
// inverse still one tick less: const LOADER: &str = "000100001001111001001111001000110101001100100011010000100111111001000110010000100011101000100001000111010001000010011110111000010000100011101000100001001111011101000100001000111010001000010011110111011000100001001111011101110010000100111101111001000111000010001111001000010011110010011110010011100";
const LOADER: &str = "0000100001001111001001111001000110101001100100011010000100111111001000110010000100011101000100001000111010001000010011110111000010000100011101000100001001111011101000100001000111010001000010011110111011000100001001111011101110010000100111101111001000111000010001111001000010011110010011110010011100";

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
        println!("starting normalization of {:?}", &rc_self);
        let result = match &*rc_self {
            Node::Sxyz(x, y, z) => Some(Node::rc_apply(Node::rc_apply(Rc::clone(&x), Rc::clone(&z)), Node::rc_apply(Rc::clone(&y), Rc::clone(&z)))),
            Node::Apply(f, x) => match Node::one_step_normalize(Rc::clone(&f)) {
                Some(nf) => Some(Node::rc_apply(nf, Rc::clone(&x))),
                _ => None,
            }
            _ => None,
        };
        println!("normalization ended with {:?}", &result);
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

    println!("starting state {:?}", &state);
    for _ in 0..max_steps {
        let maybe_new_state = Node::one_step_normalize(Rc::clone(&state));
        if maybe_new_state.is_some() {
            state = maybe_new_state.unwrap();
            println!("new state no io: {:?}\n", &state);
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
        println!("new state after io: {:?}", &state);
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

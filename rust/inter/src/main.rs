use std::env;
use std::fs::File;
use std::io::Write;
use std::rc::Rc;
use lib_inter::{Node, get_loader_node, read_txt, substitute_all};

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() != 4 {
        eprintln!("Usage: {} <input_file> <output_file> <max_steps>", args[0]);
        return;
    }
    let input_bits = read_txt(&args[1]);
    let mut output_file = File::create(&args[2]).unwrap();
    let max_steps: usize = args[3].parse().unwrap();
    let mut state = get_loader_node();
    let mut input_iter = input_bits.iter();
    for _ in 0..max_steps {
        let maybe_new_state = Node::one_step_normalize(Rc::clone(&state));
        if let Some(new_state) = maybe_new_state {
            state = new_state;
            //println!("Non-full normalization {:?}", &substitute_all(format!("{:?}", &state)));
            continue;
        }
        //println!("Full normalization {:?}", &substitute_all(format!("{:?}", &state)));
        state = match &*state {
            Node::ZeroX(x) => {
                writeln!(output_file, "0").unwrap();
                Rc::clone(&x)
            },
            Node::OneX(x) => {
                writeln!(output_file, "1").unwrap();
                Rc::clone(&x)
            },
            Node::Qxy(x, y) => {
                match input_iter.next() {
                    Some(false) => Rc::clone(&x),
                    Some(true) => Rc::clone(&y),
                    _ => panic!("End of input."),
                }
            },
            _ => {
                println!("Halted in state {:?}", &substitute_all(format!("{:?}", &state)));
                return
            },
        };
    }
    println!("Iteration limit reached in state {:?}", &substitute_all(format!("{:?}", &state)));
}

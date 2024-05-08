use std::env;
use std::rc::Rc;
use lib_inter::{Node, get_loader_node, read_txt, txt};

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() != 2 {
        eprintln!("Usage: {} <output_file>", args[0]);
        return;
    }
    let output_bits: Vec<bool> = read_txt(&args[1]);
    //println!("Output bits read: {}", txt(&output_bits));

    fn ray_search(
        state: Rc<Node>,
        input_bits: &mut Vec<bool>,
        output_bits: &Vec<bool>,
        budget: i64,
        cost: i64,
        output_bit_count: usize,
        record_output_count: &mut usize,
        record_input_count: &mut usize,
        record_split_count: &mut usize,
    ) {
        let mut new_budget = budget;
        let mut new_cost = cost;
        let mut new_state = state;
        while new_budget > 0 {
            new_budget = new_budget - new_cost;
            let maybe_new_state = Node::one_step_normalize(Rc::clone(&new_state));
            if let Some(tmp_state) = maybe_new_state {
                new_state = tmp_state;
                new_cost += 1;
                continue
            }
            break
        }
        if new_budget <= 0 {
            return;
        }
        let input_length = input_bits.len();
        let new_state = match &*new_state {
            Node::Qxy(x, y) => {
                if input_length > *record_split_count {
                    *record_split_count = input_length;
                    println!("Pointless input record {} at remaining budget {} cost {} reached by {}", input_length, new_budget, new_cost, txt(&input_bits));
                };
                new_budget = (new_budget + 1) / 2;
                input_bits.push(false);
                ray_search(Rc::clone(x), input_bits, output_bits, new_budget, new_cost, output_bit_count, record_output_count, record_input_count, record_split_count);
                input_bits.pop();
                input_bits.push(true);
                ray_search(Rc::clone(y), input_bits, output_bits, new_budget, new_cost, output_bit_count, record_output_count, record_input_count, record_split_count);
                input_bits.pop();
                return
            },
            Node::ZeroX(x) => {
                if output_bits[output_bit_count] != false {
                    //println!("Bad bit {} for {}", output_bit_count, txt(&input_bits));
                    return
                };
                x
            },
            Node::OneX(x) => {
                if output_bits[output_bit_count] != true {
                    //println!("Bad bit {} for {}", output_bit_count, txt(&input_bits));
                    return
                };
                x
            },
            _ => {
                //println!("Premature abort of {}", txt(&input_bits));
                return
            }
        };
        let new_count = output_bit_count + 1;
        //println!("DEBUG new count {}", new_count);
        if new_count >= *record_output_count && (new_count > *record_output_count || input_length < *record_input_count) {
            *record_output_count = new_count;
            *record_input_count = input_length;
            println!("New record: {} bits by {}", new_count, txt(&input_bits));
        }
        new_budget *= 9;
        new_budget /= 8;
        ray_search(Rc::clone(new_state), input_bits, output_bits, new_budget, 0, new_count, record_output_count, record_input_count, record_split_count);
    }

    let initial_state = get_loader_node();
    let mut input_bits = Vec::new();
    let mut record_output_count: usize = 0;
    let mut record_input_count: usize = 0;
    let mut record_split_count: usize = 0;
    let mut initial_budget: i64 = 1;
    loop {
        println!("Starting iteration with budget {}", initial_budget);
        ray_search(initial_state.clone(), &mut input_bits, &output_bits, initial_budget, 0, 0, &mut record_output_count, &mut record_input_count, &mut record_split_count);
        if input_bits.len() != 0 {
            panic!("Non-empty input {}", txt(&input_bits));
        }
        initial_budget *= 2;
    }
}

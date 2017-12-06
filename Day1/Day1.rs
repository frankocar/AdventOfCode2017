use std::error::Error;
use std::fs::File;
use std::io::prelude::*;
use std::path::Path;

fn main() {
    let path = Path::new("day1_input.txt");

    let mut file = match File::open(&path) {
        Err(why) => panic!("Couldn't open {}: {}", path.display(), why.description()),
        Ok(file) => file,
    };

    let mut s = String::new();
    match file.read_to_string(&mut s) {
        Err(why) => panic!("Couldn't open {}: {}", path.display(), why.description()),
        Ok(_) => s = s,
    }

    let split = s.split("");

    let numbers: Vec<i32> = split.filter_map(|x| x.parse().ok()).collect();

    println!("Step 1: {}", solve(numbers.clone(), false));
    println!("Step 2: {}", solve(numbers.clone(), true));
}

fn solve(num: Vec<i32>, step2: bool) -> i32 {
    let mut sum: i32 = 0;

    for (i, n) in num.iter().enumerate() {
        let mut tmp = 1;
        if step2 {
            tmp = num.len() / 2;
        }
        let next = (i + tmp) % num.len();

        if *n == num[next] {
           sum += n;
        }
    }

    return sum;
}
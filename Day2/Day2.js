//Run this in browser console on input page of the puzzle
const input = document.body.textContent.trim().split('\n');

let checksum1 = 0;
let checksum2 = 0;

input.forEach(line => {
  const values = line.split(/\s+/).map(x => parseInt(x)).sort((a, b) => b - a);
  checksum1 += values[0] - values[values.length - 1];

  values.forEach(x => {
    values.forEach(y => {
      if (x !== y && x % y === 0) {
        checksum2 += x / y;
      }
    })
  })
});

alert("Step 1: " + checksum1 + "\nStep 2: " + checksum2);

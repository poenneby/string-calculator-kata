const COMMA = ',';
const NEW_LINE = '\n';
const COMMA_OR_NEWLINE = /[,\n]/;

function throwNegativeNumbersError(negativeNumbers) {
  throw new Error('negatives not allowed: ' + negativeNumbers);
}

function sumOf(inputNumbers, delimiter) {
  const splitNumbers = inputNumbers.split(delimiter);
  let total = 0;
  let negativeNumbers = [];
  splitNumbers.forEach((splitNumber) => {
    const number = parseInt(splitNumber);
    if (number < 0) {
      negativeNumbers.push(number);
    }
    if (number <= 1000) {
      total += number;
    }
  });
  if (negativeNumbers.length !== 0) {
    throwNegativeNumbersError(negativeNumbers);
  }
  return total;
}

function sumOfCustomDelimited(inputNumbers) {
  const match = /\/\/(.+)\n(.+)/.exec(inputNumbers);
  const delimiter = match[1];
  const numbers = match[2];
  return sumOf(numbers, delimiter);
}

function add(inputNumbers) {
  if (inputNumbers.length === 0) {
    return 0;
  }
  if (inputNumbers.startsWith('//')) {
    return sumOfCustomDelimited(inputNumbers);
  }
  if (inputNumbers.indexOf(COMMA) !== -1 || inputNumbers.indexOf(NEW_LINE) !== -1) {
    return sumOf(inputNumbers, COMMA_OR_NEWLINE);
  }
  const number = parseInt(inputNumbers);
  if (number < 0) {
    throwNegativeNumbersError(number);
  }
  return number;
}

module.exports = {
  add,
};
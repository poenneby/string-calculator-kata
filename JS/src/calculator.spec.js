
const calculator = require('./calculator');

describe('Calculator', () => {
  it('Should return 0 for empty string', () => {
    expect(calculator.add('')).toEqual(0);
  });

  it('Should return number on single', () => {
    expect(calculator.add('1')).toEqual(1);
    expect(calculator.add('2')).toEqual(2);
  });

  it('Should return sum of comma delimited numbers', () => {
    expect(calculator.add('1,2')).toEqual(3);
    expect(calculator.add('1,2,3')).toEqual(6);
  });

  it('Should handle newline delimited numbers', () => {
    expect(calculator.add('1\n2')).toEqual(3);
    expect(calculator.add('1\n2,3')).toEqual(6);
  });

  it('Should support different delimiters', () => {
    expect(calculator.add('//;\n1;2')).toEqual(3);
    expect(calculator.add('//;\n1;2;3')).toEqual(6);
  });

  it('Should throw error for negative numbers', () => {
    try {
      calculator.add('-1');
      fail('Should throw error')
    } catch (e) {
      expect(e.message).toEqual('negatives not allowed: -1');
    }
  });

  it('Should accumulate and throw error with negative numbers', () => {
    try {
      calculator.add('1,-2,3,-4');
      fail('Should throw error');
    } catch (e) {
      expect(e.message).toEqual('negatives not allowed: ' + '-2,-4')
    }
  });

  it('Should ignore numbers bigger than 1000', () => {
    expect(calculator.add('2,1001')).toEqual(2);
    expect(calculator.add('1,1000,3')).toEqual(1004);
  });
});
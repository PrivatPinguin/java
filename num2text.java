public class NumberToWords {
    private static final String[] units = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    
    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    
    private static final String[] bigNames = {
            "", "thousand", "million", "billion"
    };
    
    public static String numberToWords(long number) {
        if (number == 0) {
            return units[0];
        } else if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        } else if (number >= 1000000000000L) {
            throw new IllegalArgumentException("Number is too large.");
        }
        
        String words = "";
        int index = 0;
        
        do {
            long threeDigits = number % 1000;
            
            if (threeDigits != 0) {
                String threeDigitsWords = convertThreeDigitsToWords((int) threeDigits);
                words = threeDigitsWords + " " + bigNames[index] + " " + words;
            }
            
            index++;
            number /= 1000;
        } while (number > 0);
        
        return words.trim();
    }
    
    private static String convertThreeDigitsToWords(int number) {
        String words = "";
    
        if (number % 100 < 20) {
            words = units[number % 100];
            number /= 100;
        } else {
            words = units[number % 10];
            number /= 10;
    
            if (number % 10 != 0) {
                words = tens[number % 10] + "-" + words;
            } else {
                words = tens[number % 10];
            }
    
            number /= 10;
        }
    
        if (number != 0) {
            if (!words.isEmpty()) {
                words = units[number] + " hundred " + words;
            } else {
                words = units[number] + " hundred";
            }
        }
    
        return words.trim().replaceAll("-zero$| zero", "");
    }
    
    
    
    
    
    public static void main(String[] args) {
        long number = 0L;
        String words = numberToWords(number);
        System.out.println(words);
    }
}

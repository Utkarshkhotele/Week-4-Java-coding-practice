import java.util.*;
import java.util.stream.*;

public class Basics {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        // filter + map + collect
        List<Integer> squaresOfEven = nums.stream()
                .filter(n -> n % 2 == 0)       // lambda: keep even
                .map(n -> n * n)               // lambda: square
                .collect(Collectors.toList());
        System.out.println(squaresOfEven);     // [4, 16, 36, 64, 100]

        // sum using reduce
        int sum = nums.stream()
                .reduce(0, (a,b) -> a + b);
        System.out.println(sum);               // 55

        // anyMatch / allMatch
        boolean anyGt8 = nums.stream().anyMatch(n -> n > 8);    // true
        boolean allPositive = nums.stream().allMatch(n -> n > 0); // true
        System.out.println(anyGt8 + " " + allPositive);
    }
}

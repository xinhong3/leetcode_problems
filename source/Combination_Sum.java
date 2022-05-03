/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 *
 * Input: candidates = [2], target = 1
 * Output: []
 *
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * All elements of candidates are distinct.
 * 1 <= target <= 500
 */

class Solution {
    private List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.ans = new ArrayList<>();
        Arrays.sort(candidates);

        backtracking(candidates, 0, 0, new ArrayList<Integer>(), target);

        return ans;
    }

    private void backtracking(int[] candidates, int start_pos, int current_sum, List<Integer> current_result, int target_sum) {
        if (current_sum == target_sum) {
            this.ans.add(new ArrayList<>(current_result));
            return;
        }

        for (int i = start_pos; i < candidates.length; i++) {
            int current_number = candidates[i];
            if (current_number + current_sum <= target_sum) {
                current_result.add(current_number);

                // start with i to allow duplicate number
                backtracking(candidates, i, current_sum + current_number, current_result, target_sum);
                current_result.remove(current_result.size() - 1);
            }
            else {

                // The numbers after current number must be greater or equal than
                // the current number, thus we can break early
                break;
            }
        }
    }
}
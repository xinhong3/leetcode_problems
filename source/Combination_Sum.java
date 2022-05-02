class Solution {
    private List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
                backtracking(candidates, i + 1, current_sum + current_number, current_result, target_sum);
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
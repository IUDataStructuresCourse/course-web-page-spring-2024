import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;

class Result {
    Result(String i, int cal, Result r) {
        item = i;
        calories = cal;
        rest = r;
    }
    String item;
    int calories;
    Result rest;
}

public class Knapsack {

    public static void print_items(HashSet<String> items) {
        for (String item : items)
            System.out.println(item);
        System.out.println("");
    }

    static Result knapsack_aux1(int availableWeight,
                                HashSet<String> remainingItems,
                                HashMap<String, Integer> weight,
                                HashMap<String, Integer> calories) {
        Result best_result = new Result("None", 0, null);
        for (String item : remainingItems) {
            if (weight.get(item) <= availableWeight) {
                HashSet<String> newRemainingItems = (HashSet<String>) remainingItems.clone();
                newRemainingItems.remove(item);
                Result rest = knapsack_aux1(availableWeight - weight.get(item),
                        newRemainingItems, weight, calories);
                int new_calories = rest.calories + calories.get(item);
                if (new_calories > best_result.calories) {
                    best_result = new Result(item, new_calories, rest);
                }
            }
        }
        return best_result;
    }

    public static HashSet<String>
    knapsack1(int availableWeight,
              HashSet<String> items,
              HashMap<String, Integer> weight,
              HashMap<String, Integer> calories) {
        Result r = knapsack_aux1(availableWeight, items, weight, calories);
        HashSet<String> choices = new HashSet<>();
        while (r != null) {
            if (r.item != "None")
                choices.add(r.item);
            r = r.rest;
        }
        return choices;
    }

    static Result knapsack_aux2(int availableWeight,
                                int nextItem,
                                ArrayList<String> items,
                                HashMap<String, Integer> weight,
                                HashMap<String, Integer> calories) {
        Result best_result = new Result("None", 0, null);
        if (nextItem < 0)
            return best_result;
        String item = items.get(nextItem);
        // Purchase the nextItem
        if (weight.get(item) <= availableWeight) {
            Result rest = knapsack_aux2(availableWeight - weight.get(item),
                    nextItem - 1, items, weight, calories);
            int new_calories = rest.calories + calories.get(item);
            if (new_calories > best_result.calories) {
                best_result = new Result(item, new_calories, rest);
            }
        }
        // Don't purchase the nextItem
        {
            Result rest = knapsack_aux2(availableWeight,
                    nextItem - 1, items, weight, calories);
            if (rest.calories > best_result.calories) {
                best_result = rest;
            }
        }
        return best_result;
    }

    public static HashSet<String>
    knapsack2(int availableWeight,
              HashSet<String> items,
              HashMap<String, Integer> weight,
              HashMap<String, Integer> calories) {
        ArrayList<String> items_array = new ArrayList<>(items);
        Result r = knapsack_aux2(availableWeight, items.size()-1, items_array, weight, calories);
        HashSet<String> choices = new HashSet<>();
        while (r != null) {
            if (r.item != "None")
                choices.add(r.item);
            r = r.rest;
        }
        return choices;
    }

    static Result knapsack_aux2_memo(int availableWeight,
                                int nextItem,
                                ArrayList<String> items,
                                HashMap<String, Integer> weight,
                                HashMap<String, Integer> calories, Result[][] R) {
        Result best_result = new Result("None", 0, null);
        if (nextItem < 0)
            return best_result;
        if (R[availableWeight][nextItem] != null)
            return R[availableWeight][nextItem];
        String item = items.get(nextItem);
        // Purchase the nextItem
        if (weight.get(item) <= availableWeight) {
            Result rest = knapsack_aux2_memo(availableWeight - weight.get(item),
                    nextItem - 1, items, weight, calories, R);
            int new_calories = rest.calories + calories.get(item);
            if (new_calories > best_result.calories) {
                best_result = new Result(item, new_calories, rest);
            }
        }
        // Don't purchase the nextItem
        {
            Result rest = knapsack_aux2_memo(availableWeight,
                    nextItem - 1, items, weight, calories, R);
            if (rest.calories > best_result.calories) {
                best_result = rest;
            }
        }
        R[availableWeight][nextItem] = best_result;
        return best_result;
    }

    public static HashSet<String>
    knapsack3(int availableWeight,
              HashSet<String> items,
              HashMap<String, Integer> weight,
              HashMap<String, Integer> calories) {
        ArrayList<String> items_array = new ArrayList<>(items);
        Result[][] R = new Result[availableWeight+1][];
        for (int i = 0; i != availableWeight+1; ++i) {
            R[i] = new Result[items.size()+1];
        }
        Result r = knapsack_aux2_memo(availableWeight, items.size()-1, items_array, weight, calories, R);
        HashSet<String> choices = new HashSet<>();
        while (r != null) {
            if (r.item != "None")
                choices.add(r.item);
            r = r.rest;
        }
        return choices;
    }

    // Dynamic Programming Version
    static Result knapsack_aux2_one(int availableWeight,
                                     int nextItem,
                                     ArrayList<String> items,
                                     HashMap<String, Integer> weight,
                                     HashMap<String, Integer> calories, Result[][] R) {
        Result best_result = new Result("None", 0, null);
        if (nextItem == 0)
            return best_result;
        if (R[availableWeight][nextItem] != null)
            return R[availableWeight][nextItem];
        String item = items.get(nextItem);
        // Purchase the nextItem
        if (weight.get(item) <= availableWeight) {
            Result rest = R[availableWeight - weight.get(item)][nextItem-1];
            int new_calories = rest.calories + calories.get(item);
            if (new_calories > best_result.calories) {
                best_result = new Result(item, new_calories, rest);
            }
        }
        // Don't purchase the nextItem
        {
            Result rest = R[availableWeight][nextItem-1];
            if (rest.calories > best_result.calories) {
                best_result = rest;
            }
        }
        R[availableWeight][nextItem] = best_result;
        return best_result;
    }

    public static HashSet<String>
    knapsack4(int availableWeight,
              HashSet<String> items,
              HashMap<String, Integer> weight,
              HashMap<String, Integer> calories) {
        ArrayList<String> items_array = new ArrayList<>(items);
        Result[][] R = new Result[availableWeight+1][];
        for (int i = 0; i != availableWeight+1; ++i) {
            R[i] = new Result[items.size()+1];
        }
        for (int i = 0; i != availableWeight+1; ++i) {
            R[i][0] = new Result("None", 0, null);
        }
        for (int nextItem = 1; nextItem != items.size(); ++ nextItem) {
            for (int availWeight = 0; availWeight != availableWeight+1; ++availWeight) {
                knapsack_aux2_one(availWeight, nextItem, items_array, weight, calories, R);
            }
        }
        Result r = R[availableWeight][items.size()-1];
        HashSet<String> choices = new HashSet<>();
        while (r != null) {
            if (r.item != "None")
                choices.add(r.item);
            r = r.rest;
        }
        return choices;
    }
}

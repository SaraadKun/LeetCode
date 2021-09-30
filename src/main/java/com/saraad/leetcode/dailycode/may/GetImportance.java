package com.saraad.leetcode.dailycode.may;


/*
// Definition for Employee.
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetImportance {
    Map<Integer, Employee> map = new HashMap<>();
    public int getImportance(List<Employee> employees, int id) {
        employees.forEach(e -> map.put(e.id, e));
        return dfs(id);
    }

    private int dfs(int id) {
        Employee employee = map.get(id);
        int ans = employee.importance;
        for (Integer sid : employee.subordinates) {
            ans += dfs(sid);
        }
        return ans;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
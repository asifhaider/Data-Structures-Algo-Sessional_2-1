/*
    Solution of Offline 10 on Dynamic Programming
    Author: Md. Asif Haider, 1805112
    Date: 2 July 2021
*/

#include <bits/stdc++.h>
using namespace std;

#define MOD 1000000007
#define DICES 100
#define SUM 10000

// global 2D array declared as the dp table with space complexity O(n*s)
int dp_table[DICES][SUM + 1];

// constructing dp table according to problem statement
int evaluate_dp_table(int arr[], int n, int sum)
{
    // time complexity O(s)
    for (int j = 1; j <= arr[0] && j <= sum; j++)
    {
        dp_table[0][j] = 1;
    }

    // time complexity O(n*s*s), commented out portion requires O(n*s)
    for (int i = 1; i < n; i++)
    {
        for (int j = 1; j <= sum; j++)
        {
            for (int k = 1; k <= arr[i] && k < j; k++)
            {
                dp_table[i][j] = ((dp_table[i][j] % MOD) + (dp_table[i - 1][j - k] % MOD) % MOD);
            }

            // cumulative sum storing technique to reduce time complexity

            // dp_table[i][j] = (dp_table[i][j - 1] + dp_table[i - 1][j - 1]) % MOD;
            // if (j - arr[i] - 1 > 0)
            // {
            //     dp_table[i][j] = (dp_table[i][j] - dp_table[i - 1][j - arr[i] - 1]) % MOD;
            // }
        }
    }
    return dp_table[n - 1][sum];
}

int main()
{
    int n, s;
    ifstream input;
    input.open("input.txt");
    input >> n >> s;
    int arr[n];
    for (int i = 0; i < n; i++)
    {
        input >> arr[i];
    }
    input.close();
    int result = evaluate_dp_table(arr, n, s) % MOD;
    cout << result << endl;
    return 0;
}
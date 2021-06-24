/*
    Solution of Offline 9 on Greedy Techniques
    Author: Md. Asif Haider, 1805112
    Date: 24 June 2021
*/

#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n, k, multiple = 1; // setting the multiplier as 1 to ensure the original prices while buying first time
    cin >> n >> k;
    int ara[n];
    for (int i = 0; i < n; i++)
    {
        cin >> ara[i];
    }
    long long int total_price = 0;
    
    // Greedy approach: counting the higher prices with multipliers as low as possible to minimize cost
    // STL sort, third parameter used for sorting in descending order 
    sort(ara, ara+n, greater<int>());

    for (int i = 0, j = k; i < n; i++, j--)
    {
        // each round of buying ends by all friends and the later values are counted with increased multiplier 
        if (j == 0)
        {
            j = k;
            multiple++;
        }
        total_price += (ara[i] * multiple);
    }
    cout << total_price << endl;
    return 0;
}
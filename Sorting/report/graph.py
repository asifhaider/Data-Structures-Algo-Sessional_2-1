import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import seaborn as sns


def read_output():
    input_size = []
    average_time = []
    sorting = []
    file = open("report.txt", "r")
    line = file.read().splitlines()
    for i in line:
        i = i.split(":")
        values = i[1].split(", ")
        for j in values:
            input_size.append(int(i[0]))
            average_time.append(float(j)/1000)
        sorting.append('Ascending Order Merge Sort')
        sorting.append('Ascending Order Quick Sort')
        sorting.append('Descending Order Merge Sort')
        sorting.append('Descending Order Quick Sort')
        sorting.append('Random Order Merge Sort')
        sorting.append('Random Order Quick Sort')
                
    print(input_size)
    print(average_time)
    print(sorting)
    data = {'Input Size': input_size,
            'Sorting': sorting,
            'Average Running Time': average_time
            }
    df = pd.DataFrame(data)
    print(df)
    return df


def line_plot():
    data = read_output()
    data = data.pivot("Input Size", "Sorting", "Average Running Time")
    sns.set_theme(style="darkgrid")
    sns.lineplot(data=data)
    plt.xlabel('Input Size (integer)')
    plt.ylabel("Average Running Time (second)")
    plt.title('Merge Sort vs Quick Sort')
    plt.legend(loc="upper left")
    plt.show()
    

if __name__ == '__main__':
    line_plot()

/*
    Solution of Offline 5 on Data Structure: Heap and Priority Queue
    Author: Md. Asif Haider, 1805112
    Date: 9 April 2021
*/

#include<vector>
#include<iostream>
#include<fstream>

using namespace std;

// Heap class signature
class Heap
{
private:
    int capacity;   // capacity defined while creating instance
    int current_size;   // size of the array
    int *heap_array;    // array pointer for storing heap items

// public method declaration
public:
    Heap(int n);
    void insert(int element);
    int size();
    int getMax();
    void deleteKey();
    int extract_root(); // provides the max value and further heapifies the existing max heap
    ~Heap();
};

// Global functions definitions
int left_child_index(int index)
{
    return 2*index + 1;
}

int right_child_index(int index)
{
    return 2*index + 2;
}

int parent_index(int index)
{
    return (index-1)/2;
}

void quick_swap (int &a, int &b)
{
    int temp = a;
    a = b;
    b = temp;
}

// shift down MAX_HEAPIFY function
void max_heapify(int *array, int length, int index)
{
    int left, right, largest_of_three;

    // comparing a node with its two children value and proceed
    while(index < length)
    {
        left = left_child_index(index);
        right = right_child_index(index);
        largest_of_three = index;

        if(left < length && (array[left] > array[index]))
        {
            largest_of_three = left;
        }

        if(right < length && (array[right] > array[largest_of_three]))
        {
            largest_of_three = right;
        }

        if(largest_of_three != index)
        {
            quick_swap(array[index], array[largest_of_three]);
            index = largest_of_three;
        }
        else
            break;
    }
}


// shift up single element heapify function
void bottom_up_heapify(int *array, int length, int index)
{
    int parent;
    // comparing a node with its parent node value and proceed
    while(index > 0)
    {
        parent = parent_index(index);
        if(array[index] > array[parent])
        {
            quick_swap(array[index], array[parent]);
            index = parent;
        }
        else
            break;
    }
}

// Original Heap Sort Function
void heapsort(vector<int>&v)
{
    Heap heap(v.size());

    // inserting the vector elements
    for (int i=0; i<v.size(); i++)
    {
        heap.insert(v[i]);  // building a max heap
    }

    for (int i=0; i<v.size(); i++)
    {
        v[i] = heap.extract_root();     // ensures descending heap sort
    }
}


// Heap class public method definitions
// Constructor with maximum size parameter
Heap::Heap(int n)
{
    this->capacity = n; // capacity updated
    this->current_size = 0;
    this->heap_array = new int [n];
}


// inserting an element into heap and verifying the max heap property
void Heap::insert(int element)
{
    if(this->current_size == this->capacity)
    {
        cout << "Maximum Capacity Reached!" << endl;
        return;
    }
    heap_array[current_size++] = element;
    bottom_up_heapify(heap_array, current_size, current_size-1);
}

// extracting current array size
int Heap::size()
{
    return this->current_size;
}

// maximum value should be the root node of the max heap binary tree
int Heap::getMax()
{
    if(this->current_size == 0)
    {
        return INT_MIN;
    }
    return this->heap_array[0];
}

// deleting the root node and verifying the max heap property
void Heap::deleteKey()
{
    heap_array[0] = heap_array[current_size-1];
    current_size--;
    max_heapify(heap_array, current_size, 0);
}

// descending heap sort helper method
int Heap::extract_root()
{
    int value = heap_array[0];
    quick_swap(heap_array[0], heap_array[current_size-1]);
    current_size--;
    max_heapify(heap_array, current_size, 0);
    return value;
}

// instance destructor
Heap::~Heap()
{
    delete[] heap_array;
}

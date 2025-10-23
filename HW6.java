public class HW6 {
   public static void main(String[] args) {
      // TEST CASE #1: capacity = 1
      Solution sol = new Solution(1);
      System.out.println(sol.getFront()); // -1
      System.out.println(sol.getRear());  // -1
      sol.add(8);
      System.out.println(sol.getFront()); // 0
      System.out.println(sol.getRear());  // 0
      System.out.println(sol.peek());     // 8
      System.out.println(sol.remove());   // 8
      System.out.println(sol.isEmpty());  // true
      System.out.println(sol.getFront()); // -1
      System.out.println(sol.getRear());  // -1

      System.out.println("----");

      // TEST CASE #2: capacity = 3
      Solution sol2 = new Solution(3);
      sol2.add(1);
      sol2.add(2);
      sol2.add(3);
      System.out.println(sol2.getFront()); // 0
      System.out.println(sol2.getRear());  // 2
      System.out.println(sol2.peek());     // 1
      System.out.println(sol2.remove());   // 1
      System.out.println(sol2.getFront()); // 1
      System.out.println(sol2.getRear());  // 2
      sol2.add(4);                         // circular wrap
      System.out.println(sol2.getFront()); // 1
      System.out.println(sol2.getRear());  // 0 (after wrap)
      System.out.println(sol2.peek());     // 2
   }
}

class Solution {
   // Fixed array capacity
   private int capacity;
   private int[] elements;
   private int numElements = 0;
   private int front = -1;
   private int rear = -1;

   // Constructor
   public Solution(int capacity) {
      this.capacity = capacity;
      this.elements = new int[this.capacity];
   }

   // Getters (DO NOT MODIFY)
   public int getFront() { return this.front; }
   public int getRear()  { return this.rear; }

   /**
    * PURPOSE: Add an element to the rear of the queue
    * PARAMETERS: int x (the element to be added)
    * RETURN VALUES: none
    */
   public void add(int x) {
      if (numElements == capacity) return; // full (not required to handle resize)
      if (isEmpty()) {
         front = 0;
         rear = 0;
      } else {
         rear = (rear + 1) % capacity;
      }
      elements[rear] = x;
      numElements++;
   }

   /**
    * PURPOSE: Remove and return the front element from the queue
    * PARAMETERS: none
    * RETURN VALUES: int (removed value)
    */
   public int remove() {
      if (isEmpty()) return -1; // no exceptions per spec
      int val = elements[front];
      numElements--;
      if (numElements == 0) {
         front = -1;
         rear = -1;
      } else {
         front = (front + 1) % capacity;
      }
      return val;
   }

   /**
    * PURPOSE: Peek (read-only) the front element without removing it
    * PARAMETERS: none
    * RETURN VALUES: int (the front element)
    */
   public int peek() {
      if (isEmpty()) return -1;
      return elements[front];
   }

   /**
    * PURPOSE: Check if queue is empty
    * PARAMETERS: none
    * RETURN VALUES: boolean
    */
   public boolean isEmpty() {
      return numElements == 0;
   }
}

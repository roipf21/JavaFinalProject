public class CircularListReferenceBased 
{
   private Node firstPlayer;
   private Node small;
   private Node curr;
   private int numObj;
   /**
      Constructor for the ListReferenceBased class
   */
   public CircularListReferenceBased()
   {
      firstPlayer = null;
      small = null;
      curr = null;
      numObj = 0;
   }
   /**
      Method that checks if the List has already Nodes created or not
      @return true if it has no Nodes in it
   */
   public boolean isEmpty()
   {
      return firstPlayer == null;
   }
   /**
      Method to check the size of the List, amount of Nodes chained
      @return The number of Nodes in the list
   */
   public int size()
   {
      return numObj;
   }
   /**
      Method to add a Node in the desired position
      @param index The position where we want to add the index
      @param item The object we want to put in the Node
      @exception ListIndexOutOfBoundsException When the index given is out of the actual bounds
   */
   public void add(Object item)
   {
      Node newNode = new Node(item); //Node to add
      curr = firstPlayer; //current = first
      int i = 1; //variable to control number of times the loop runs
      if (isEmpty()) //add the first
      {
         newNode.setNext(newNode);
         firstPlayer = newNode;
      }
      else //add in any other position
      {
         while ( i < numObj )
         {
           curr = curr.getNext();
           i++;
         }
         newNode.setNext(curr.getNext());
         curr.setNext(newNode);
      }
      numObj++;
   }
   /**
      Method to get the object inside the Node
      @param index Number of the node where we want to take the item from
      @return A reference to the object 
      @exception ListIndexOutOfBoundsException When the index is not a valid one
   */
   public Object get(int index) throws ListIndexOutOfBoundsException
   {
      
      curr = firstPlayer;
      int i = 1;
      if (index >= 1 && index <= numObj)
      {
         while ( i < index )
         {
            curr = curr.getNext();
            i++;
         }
         Object item = curr.getItem(); 
         return item;
      }
      else
      {
         throw new ListIndexOutOfBoundsException("not in list");
      }
   }
   /**
      Method to remove one Node from the ReferenceBasedList
      @param index Node we want to remove
      @exception ListIndexOutOfBoundsException When th index given is out of bounds in the list
   */
   public void remove(int index) throws ListIndexOutOfBoundsException
   {
      curr = firstPlayer;
      int i = 1;
      if (index >= 1 && index <= numObj+1)
      { 
         if ( index == 1 )
         {  
            while ( i < numObj )
            {
              curr = curr.getNext();
              i++;
            }
            curr.setNext(firstPlayer.getNext());
            firstPlayer= firstPlayer.getNext();
         }
         else
         {
            while ( i < index-1 )
            {
               curr = curr.getNext();
               i++;
            }//after this loop curr points to the node before the one to delete (toRemove)
            
            Node toRemove = curr.getNext(); //We create a reference to the Node after curr, the one to be deleted
            curr.setNext(toRemove.getNext()); //We give the setNext() method of curr the reference to the Node after the one we want to delete
            //now we want to delete conexions to ensure that is going to be deleted by the collector
            toRemove.setNext(null); //With this the Node refers null
            toRemove = null; //With this the reference to the Node becomes null so we loose its reference
         }
         numObj--;
      }
      else
      {
         throw new ListIndexOutOfBoundsException("not in list");
      }   
   }
   /**
      Method to reset the list.
   */
   public void removeAll()
   {
      firstPlayer = null;
      curr = firstPlayer;
   }
   /**
      Method to set the Small blind
      @param index The player who is the Small blind
   */
   public void setSmall(int index)
   {
      small = (Node)get(index);
   }
}
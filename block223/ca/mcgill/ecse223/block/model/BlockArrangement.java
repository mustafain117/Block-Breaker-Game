/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;
import java.util.*;

// line 59 "../../../../../umple.ump"
public class BlockArrangement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BlockArrangement Attributes
  private String gridPosition;
  private int numberOfBlocks;

  //BlockArrangement Associations
  private List<Block> blocks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BlockArrangement(String aGridPosition, int aNumberOfBlocks)
  {
    gridPosition = aGridPosition;
    numberOfBlocks = aNumberOfBlocks;
    blocks = new ArrayList<Block>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setGridPosition(String aGridPosition)
  {
    boolean wasSet = false;
    gridPosition = aGridPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfBlocks(int aNumberOfBlocks)
  {
    boolean wasSet = false;
    numberOfBlocks = aNumberOfBlocks;
    wasSet = true;
    return wasSet;
  }

  public String getGridPosition()
  {
    return gridPosition;
  }

  public int getNumberOfBlocks()
  {
    return numberOfBlocks;
  }
  /* Code from template association_GetMany */
  public Block getBlock(int index)
  {
    Block aBlock = blocks.get(index);
    return aBlock;
  }

  public List<Block> getBlocks()
  {
    List<Block> newBlocks = Collections.unmodifiableList(blocks);
    return newBlocks;
  }

  public int numberOfBlocks()
  {
    int number = blocks.size();
    return number;
  }

  public boolean hasBlocks()
  {
    boolean has = blocks.size() > 0;
    return has;
  }

  public int indexOfBlock(Block aBlock)
  {
    int index = blocks.indexOf(aBlock);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBlocks()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBlock(Block aBlock)
  {
    boolean wasAdded = false;
    if (blocks.contains(aBlock)) { return false; }
    blocks.add(aBlock);
    if (aBlock.indexOfBlockArrangement(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBlock.addBlockArrangement(this);
      if (!wasAdded)
      {
        blocks.remove(aBlock);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBlock(Block aBlock)
  {
    boolean wasRemoved = false;
    if (!blocks.contains(aBlock))
    {
      return wasRemoved;
    }

    int oldIndex = blocks.indexOf(aBlock);
    blocks.remove(oldIndex);
    if (aBlock.indexOfBlockArrangement(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBlock.removeBlockArrangement(this);
      if (!wasRemoved)
      {
        blocks.add(oldIndex,aBlock);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBlockAt(Block aBlock, int index)
  {  
    boolean wasAdded = false;
    if(addBlock(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBlockAt(Block aBlock, int index)
  {
    boolean wasAdded = false;
    if(blocks.contains(aBlock))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBlocks()) { index = numberOfBlocks() - 1; }
      blocks.remove(aBlock);
      blocks.add(index, aBlock);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBlockAt(aBlock, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Block> copyOfBlocks = new ArrayList<Block>(blocks);
    blocks.clear();
    for(Block aBlock : copyOfBlocks)
    {
      aBlock.removeBlockArrangement(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "gridPosition" + ":" + getGridPosition()+ "," +
            "numberOfBlocks" + ":" + getNumberOfBlocks()+ "]";
  }
}
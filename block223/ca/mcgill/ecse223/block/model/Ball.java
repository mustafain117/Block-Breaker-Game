/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 65 "../../../../../umple.ump"
public class Ball
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int Diameter = 10;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ball Attributes
  private int minSpeed;
  private int speedIncreaseFactor;

  //Ball Associations
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ball(int aMinSpeed, int aSpeedIncreaseFactor, Admin aAdmin)
  {
    minSpeed = aMinSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    if (aAdmin == null || aAdmin.getBall() != null)
    {
      throw new RuntimeException("Unable to create Ball due to aAdmin");
    }
    admin = aAdmin;
  }

  public Ball(int aMinSpeed, int aSpeedIncreaseFactor, User aUserForAdmin, Paddle aPaddleForAdmin, Game aGameForAdmin)
  {
    minSpeed = aMinSpeed;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    admin = new Admin(aUserForAdmin, this, aPaddleForAdmin, aGameForAdmin);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinSpeed(int aMinSpeed)
  {
    boolean wasSet = false;
    minSpeed = aMinSpeed;
    wasSet = true;
    return wasSet;
  }

  public boolean setSpeedIncreaseFactor(int aSpeedIncreaseFactor)
  {
    boolean wasSet = false;
    speedIncreaseFactor = aSpeedIncreaseFactor;
    wasSet = true;
    return wasSet;
  }

  public int getMinSpeed()
  {
    return minSpeed;
  }

  public int getSpeedIncreaseFactor()
  {
    return speedIncreaseFactor;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }

  public void delete()
  {
    Admin existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "minSpeed" + ":" + getMinSpeed()+ "," +
            "speedIncreaseFactor" + ":" + getSpeedIncreaseFactor()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}
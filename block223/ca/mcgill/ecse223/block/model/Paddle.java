/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse223.block.model;

// line 72 "../../../../../umple.ump"
public class Paddle
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  public static final int Width = 5;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Paddle Attributes
  private int maxLength;
  private int minLegth;
  private int minLength;

  //Paddle Associations
  private Admin admin;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Paddle(int aMaxLength, int aMinLegth, int aMinLength, Admin aAdmin)
  {
    maxLength = aMaxLength;
    minLegth = aMinLegth;
    minLength = aMinLength;
    if (aAdmin == null || aAdmin.getPaddle() != null)
    {
      throw new RuntimeException("Unable to create Paddle due to aAdmin");
    }
    admin = aAdmin;
  }

  public Paddle(int aMaxLength, int aMinLegth, int aMinLength, User aUserForAdmin, Ball aBallForAdmin, Game aGameForAdmin)
  {
    maxLength = aMaxLength;
    minLegth = aMinLegth;
    minLength = aMinLength;
    admin = new Admin(aUserForAdmin, aBallForAdmin, this, aGameForAdmin);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMaxLength(int aMaxLength)
  {
    boolean wasSet = false;
    maxLength = aMaxLength;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinLegth(int aMinLegth)
  {
    boolean wasSet = false;
    minLegth = aMinLegth;
    wasSet = true;
    return wasSet;
  }

  public boolean setMinLength(int aMinLength)
  {
    boolean wasSet = false;
    minLength = aMinLength;
    wasSet = true;
    return wasSet;
  }

  public int getMaxLength()
  {
    return maxLength;
  }

  public int getMinLegth()
  {
    return minLegth;
  }

  public int getMinLength()
  {
    return minLength;
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
            "maxLength" + ":" + getMaxLength()+ "," +
            "minLegth" + ":" + getMinLegth()+ "," +
            "minLength" + ":" + getMinLength()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "admin = "+(getAdmin()!=null?Integer.toHexString(System.identityHashCode(getAdmin())):"null");
  }
}
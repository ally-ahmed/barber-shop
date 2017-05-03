import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.*;

public class StylistTest{
	@Rule 
	public DatabaseRule database = new DatabaseRule();
	
	@Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("me");
    assertEquals(true, testStylist instanceof Stylist);
  }
	
	
  @Test
  public void getName_stylistInstantiatesWithName_me() {
    Stylist testStylist = new Stylist("me");
    assertEquals("me", testStylist.getName());
  }
	
	@Test
	public void all_returnsAllInstancesOfCategory_true() {
   Stylist firstStylist = new Stylist("me");
	 firstStylist.save();
   Stylist secondStylist = new Stylist("you");
	 secondStylist.save();
	 assertEquals(true, Stylist.all().get(0).equals(firstStylist));
	 assertEquals(true, Stylist.all().get(1).equals(secondStylist));
	}
	
	@Test
	public void getId_stylistsInstantiateWithAnId_1() {
	 Stylist testStylist = new Stylist("me");
	 testStylist.save();
	 assertTrue(testStylist.getId() > 0);
	}
	
	@Test
	public void find_returnsStylistWithSameId_secondStylist() {
		Stylist firstStylist = new Stylist("me");
		firstStylist.save();
		Stylist secondStylist = new Stylist("you");
		secondStylist.save();
		assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
	}
	
	@Test
  public void equals_returnsTrueIfNamesAretheSame() {
		Stylist firstStylist = new Stylist("me");
		Stylist secondStylist = new Stylist("me");
    assertTrue(firstStylist.equals(secondStylist));
  }
	
	@Test
  public void save_savesIntoDatabase_true() {
    Stylist testStylist = new Stylist("me");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }
	
	@Test
  public void save_assignsIdToObject() {
 		Stylist testStylist = new Stylist("me");
    testStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(testStylist.getId(), savedStylist.getId());
  }
	
	@Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
   	Stylist testStylist = new Stylist("him");
    testStylist.save();
   	Client firstClient = new Client("me", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("you", testStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] {firstClient, secondClient};
    assertEquals(true,testStylist.getClients().containsAll(Arrays.asList(clients)));
  }
	
}

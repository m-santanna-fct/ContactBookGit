package contactBook;

import contactBook.Contact;

public class ContactBook {
    static final int DEFAULT_SIZE = 100;
    public static final String NAME_SEARCH = "NAME";
    public static final String PHONE_SEARCH = "PHONE";

    private int counter;
    private Contact[] contacts;
    private int currentContact;

    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
        currentContact = -1;
    }

    //Pre: name != null
    public boolean hasContact(String name, String searchType) {
        return searchIndex(name,searchType) >= 0;
    }

    public int getNumberOfContacts() {
        return counter;
    }

    //Pre: name!= null && !hasContact(name)
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    //Pre: name != null && hasContact(name)
    public void deleteContact(String name) {
        int index = searchIndex(name,NAME_SEARCH);
        for(int i=index; i<counter; i++)
            contacts[i] = contacts[i+1];
        counter--;
    }

    //Pre: name != null && hasContact(name)
    public int getPhone(String name) {
        return contacts[searchIndex(name,NAME_SEARCH)].getPhone();
    }
    public Contact getContactFromPhone(String number){
        int index = searchIndex(number,PHONE_SEARCH);
        if (index >= 0){
            return contacts[index];
        }
      return null;
    }

    //Pre: name != null && hasContact(name)
    public String getEmail(String name) {
        return contacts[searchIndex(name,NAME_SEARCH)].getEmail();
    }

    //Pre: name != null && hasContact(name)
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name,NAME_SEARCH)].setPhone(phone);
    }

    //Pre: name != null && hasContact(name)
    public void setEmail(String name, String email) {
        contacts[searchIndex(name,NAME_SEARCH)].setEmail(email);
    }

    private int searchIndex(String name, String searchType) {
        switch (searchType) {
            case NAME_SEARCH:
                for (int i = 0; i < counter; i++) {
                    if (contacts[i].getName().equals(name)) {
                        return i;
                    }
                }
                break;
            case PHONE_SEARCH:
                for (int i = 0; i < counter; i++) {
                    if (String.valueOf(contacts[i].getPhone()).equals(name)) {
                        return i;
                    }
                }
                break;
        }
        return -1;
    }

    private void resize() {
        Contact tmp[] = new Contact[2*contacts.length];
        for (int i=0;i<counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }

    public void initializeIterator() {
        currentContact = 0;
    }

    public boolean hasNext() {
        return (currentContact >= 0 ) && (currentContact < counter);
    }

    //Pre: hasNext()
    public Contact next() {
        return contacts[currentContact++];
    }

}

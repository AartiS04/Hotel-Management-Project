package model;

public class Customer {
    private final String fn;
    private final String ln;
    private final String em;

    public Customer(String fn, String ln, String em) {
        if (!em.matches("^[A-Za-z0-9+_.-]+@(.+).com$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.fn = fn;
        this.ln = ln;
        this.em = em;
    }

    public String getEmail() {
        return em;
    }

    @Override
    public String toString() {
        return fn + " " + ln + " (" + em + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Customer))
            return false;
        Customer other = (Customer) obj;
        return em.equalsIgnoreCase(other.em);
    }

    @Override
    public int hashCode() {
        return em.toLowerCase().hashCode();
    }
}
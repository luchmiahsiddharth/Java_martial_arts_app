package Code;
public class Instructor extends User{
    
    public Instructor(String username, String firstName, String lastName, String dateJoined, String email, String birthDate, String loginPassword, String role) {
        super(username, firstName, lastName, dateJoined, email, birthDate, loginPassword, role);
    }

    @Override
    public void dashboard() {
        String[] args = null;
        InstructorDashboard.main(args);
    }
}

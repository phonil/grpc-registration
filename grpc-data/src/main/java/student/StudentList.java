package student;

import com.phonil.registration.StudentInfo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentList {

	protected ArrayList<Student> vStudent;

	public StudentList(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader objStudentFile = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (objStudentFile.ready()) {
			String stuInfo = objStudentFile.readLine();
			if (!stuInfo.equals("")) {
				this.vStudent.add(new Student(stuInfo));
			}
		}
		objStudentFile.close();
	}

//	public ArrayList<Student> getAllStudentRecords() {
//		return this.vStudent;
//	}

	public List<StudentInfo> getAllStudentRecords() {
//		if (this.vStudent.size() == 0) throw new NullDataException("~~~ Student Data Is Null ~~~");
		List<Student> students = this.vStudent;
		return students.stream()
				.map(Student::toStudentInfo) // Student 객체를 StudentInfo로 변환
				.collect(Collectors.toList());
	}

	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			Student objStudent = (Student) this.vStudent.get(i);
			if (objStudent.match(sSID)) {
				return true;
			}
		}
		return false;
	}
}

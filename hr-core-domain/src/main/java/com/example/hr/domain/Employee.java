package com.example.hr.domain;

// Entity: Have identity, Mutable Class
// Entity Root -> Aggregate
public class Employee {
	private final Identity identity;
	private Fullname fullname;
	private Money salary;
	private Iban iban;
	private BirthYear birthYear;
	private Photo photo;
	private boolean fulltime;
	private Department department;

	public Employee(Identity identity, Fullname fullname, Money salary, BirthYear birthYear) {
		this.identity = identity;
		this.fullname = fullname;
		this.salary = salary;
		this.birthYear = birthYear;
	}

	public Employee(Identity identity, Fullname fullname, Money salary, Iban iban, BirthYear birthYear, Photo photo,
			boolean fulltime, Department department) {
		this.identity = identity;
		this.fullname = fullname;
		this.salary = salary;
		this.iban = iban;
		this.birthYear = birthYear;
		this.photo = photo;
		this.fulltime = fulltime;
		this.department = department;
	}

	public Employee(Builder builder) {
		this.identity = builder.identity;
		this.fullname = builder.fullname;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.birthYear = builder.birthYear;
		this.photo = builder.photo;
		this.fulltime = builder.fulltime;
		this.department = builder.department;
	}

	// Builder Pattern
	public static class Builder {
		private final Identity identity;
		private Fullname fullname;
		private Money salary;
		private Iban iban;
		private BirthYear birthYear;
		private Photo photo;
		private boolean fulltime;
		private Department department;

		public Builder(Identity identity) {
			this.identity = identity;
		}

		public Builder fullname(String first, String last) {
			this.fullname = new Fullname(first, last);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Money.valueOf(value);
			return this;
		}

		public Builder salary(double value, MoneyCurrency currency) {
			this.salary = Money.valueOf(value, currency);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.valueOf(value);
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = Photo.valueOf(values);
			return this;
		}

		public Builder fulltime(boolean isFulltime) {
			this.fulltime = isFulltime;
			return this;
		}

		public Builder department(Department dept) {
			this.department = dept;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}

	public Fullname getFullname() {
		return fullname;
	}

	public void setFullname(Fullname fullname) {
		this.fullname = fullname;
	}

	public Money getSalary() {
		return salary;
	}

	public void setSalary(Money salary) {
		this.salary = salary;
	}

	public Iban getIban() {
		return iban;
	}

	public void setIban(Iban iban) {
		this.iban = iban;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(BirthYear birthYear) {
		this.birthYear = birthYear;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public boolean isFulltime() {
		return fulltime;
	}

	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Identity getIdentity() {
		return identity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identity == null) ? 0 : identity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (identity == null) {
			if (other.identity != null)
				return false;
		} else if (!identity.equals(other.identity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [identity=" + identity + ", fullname=" + fullname + ", salary=" + salary + ", iban=" + iban
				+ ", birthYear=" + birthYear + ", fulltime=" + fulltime + ", department=" + department + "]";
	}

}

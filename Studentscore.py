# Student Result Management System

def calculate_grade(p):
    if p >= 90: return "A"
    elif p >= 75: return "B"
    elif p >= 60: return "C"
    else: return "D"

students = []

while True:
    print("\n1.Add Student  2.View Results  3.Exit")
    ch = int(input("Enter choice: "))

    if ch == 1:
        name = input("Name: ")
        m1 = int(input("Maths: "))
        m2 = int(input("Science: "))
        m3 = int(input("English: "))
        
        total = m1 + m2 + m3
        per = total / 3
        grade = calculate_grade(per)

        record = f"{name} - Total:{total} Percentage:{per:.2f} Grade:{grade}"
        students.append(record)

        with open("results.txt", "a") as f:
            f.write(record + "\n")

        print("Student added!\n")

    elif ch == 2:
        print("\n--- Student Results ---")
        for s in students:
            print(s)

    elif ch == 3:
        break

    else:
        print("Invalid choice!")

import json
from datetime import datetime

FILE = "expenses.json"

# Load data
try:
    with open(FILE, "r") as f:
        expenses = json.load(f)
except:
    expenses = []

def add_expense():
    amount = float(input("Enter amount: "))
    category = input("Category (food/travel/shopping/bills/other): ")
    note = input("Note: ")
    
    entry = {
        "amount": amount,
        "category": category,
        "note": note,
        "date": datetime.now().strftime("%Y-%m-%d")
    }

    expenses.append(entry)
    with open(FILE, "w") as f:
        json.dump(expenses, f, indent=4)

    print("Expense Added!")

def view_summary():
    total = sum(e["amount"] for e in expenses)
    print(f"\nTotal Spent: ₹{total}")

    categories = {}
    for e in expenses:
        categories[e["category"]] = categories.get(e["category"], 0) + e["amount"]

    print("\nCategory-wise Spending:")
    for cat, amt in categories.items():
        print(f"{cat}: ₹{amt}")

def view_all():
    print("\n--- All Expenses ---")
    for e in expenses:
        print(f"{e['date']} - ₹{e['amount']} ({e['category']}) : {e['note']}")

while True:
    print("\n1.Add Expense  2.View Summary  3.View All  4.Exit")
    ch = int(input("Choose: "))

    if ch == 1: add_expense()
    elif ch == 2: view_summary()
    elif ch == 3: view_all()
    elif ch == 4: break
    else: print("Invalid choice!")

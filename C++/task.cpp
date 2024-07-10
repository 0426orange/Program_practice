#include <iostream>
#include <vector>
#include <string>
#include <iostream>

class Task {
public:
    Task(const std::string& name, const std::string& description)
        : name(name), description(description) {}

    void display() const {
        std::cout << "Task: " << name << "\nDescription: " << description << std::endl;
    }

    std::string getName() const {
        return name;
    }

private:
    std::string name;
    std::string description;
};


class TaskManager {
public:
    void addTask(const std::string& name, const std::string& description) {
        tasks.emplace_back(name, description);
    }

    void viewTasks() const {
        if (tasks.empty()) {
            std::cout << "No tasks available." << std::endl;
        } else {
            for (const auto& task : tasks) {
                task.display();
                std::cout << "------------------" << std::endl;
            }
        }
    }

    void removeTask(const std::string& name) {
        auto it = std::remove_if(tasks.begin(), tasks.end(),
                                 [&name](const Task& task) { return task.getName() == name; });

        if (it != tasks.end()) {
            tasks.erase(it, tasks.end());
            std::cout << "Task removed successfully." << std::endl;
        } else {
            std::cout << "Task not found." << std::endl;
        }
    }

private:
    std::vector<Task> tasks;
};


int main() {
    TaskManager taskManager;
    int choice;
    std::string name, description;

    while (true) {
        std::cout << "Task Manager\n";
        std::cout << "1. Add Task\n";
        std::cout << "2. View Tasks\n";
        std::cout << "3. Remove Task\n";
        std::cout << "4. Exit\n";
        std::cout << "Enter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1:
                std::cout << "Enter task name: ";
                std::cin.ignore();
                std::getline(std::cin, name);
                std::cout << "Enter task description: ";
                std::getline(std::cin, description);
                taskManager.addTask(name, description);
                std::cout << "Task added successfully." << std::endl;
                break;
            case 2:
                taskManager.viewTasks();
                break;
            case 3:
                std::cout << "Enter task name to remove: ";
                std::cin.ignore();
                std::getline(std::cin, name);
                taskManager.removeTask(name);
                break;
            case 4:
                std::cout << "Exiting program." << std::endl;
                return 0;
            default:
                std::cout << "Invalid choice. Please try again." << std::endl;
        }
    }

    return 0;
}

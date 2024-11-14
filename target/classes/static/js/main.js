// main.js
// Entry point of the application, initializes page elements and loads all modules

document.addEventListener('DOMContentLoaded', function() {
    // Load all tasks when the page is loaded
    taskManager.loadTasks();

    // Handle Add Task button click
    taskManager.handleAddTask();

    // Handle Edit and Delete task actions
    taskManager.handleTaskActions();

    // Attach search and filter events
    searchFilter.filterTasks();

    // Handle log out button
    const logoutButton = document.getElementById('logout_btn');
    if (logoutButton) {
        logoutButton.addEventListener('click', function() {
            window.location.href = './login.html';
        });
    }

    // Add reset button functionality
    const resetButton = document.getElementById('show_all_btn');
    if (resetButton) {
        resetButton.addEventListener('click', searchFilter.clearFilters);
    }

    // Attach modal functionality
    const addTaskButton = document.getElementById('add_task_btn');
    if (addTaskButton) {
        addTaskButton.addEventListener('click', modalManager.showAddTaskModal);
    }
});
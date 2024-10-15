// taskManager.js
// Responsible for handling tasks: add, edit, delete, and load tasks

const taskManager = (function() {
    let tasks = [];

    // Load tasks from localStorage
    function loadTasks() {
        const storedTasks = localStorage.getItem('tasks');
        if (storedTasks) {
            tasks = JSON.parse(storedTasks);
            renderTasks();
        }
    }

    // Save tasks to localStorage
    function saveTasks() {
        localStorage.setItem('tasks', JSON.stringify(tasks));
    }

    // Render tasks to the table
    function renderTasks() {
        const taskTableBody = document.querySelector('.tab_body');
        taskTableBody.innerHTML = '';
        tasks.forEach((task, index) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${task.name}</td>
                <td>${task.category}</td>
                <td>${task.deadline}</td>
                <td>
                    <button class="edit_btn" data-index="${index}">Edit</button>
                    <button class="delete_btn" data-index="${index}">Delete</button>
                </td>
            `;
            taskTableBody.appendChild(row);
        });
    }

    // Add a new task
    function addTask(name, category, deadline) {
        tasks.push({ name, category, deadline });
        saveTasks();
        renderTasks();
    }

    // Edit an existing task
    function editTask(index, updatedTask) {
        tasks[index] = updatedTask;
        saveTasks();
        renderTasks();
    }

    // Delete a task
    function deleteTask(index) {
        tasks.splice(index, 1);
        saveTasks();
        renderTasks();
    }

    // Handle Add Task button click
    function handleAddTask() {
        const addTaskButton = document.getElementById('add_task_btn');
        if (addTaskButton) {
            addTaskButton.addEventListener('click', function() {
                modalManager.showAddTaskModal();
            });
        }
    }

    // Handle task actions (edit, delete)
    function handleTaskActions() {
        const taskTableBody = document.querySelector('.tab_body');
        taskTableBody.addEventListener('click', function(event) {
            const target = event.target;
            const index = target.getAttribute('data-index');

            if (target.classList.contains('edit_btn')) {
                modalManager.showEditTaskModal(index, tasks[index]);
            }

            if (target.classList.contains('delete_btn')) {
                modalManager.handleDelete(index); // update here to call
            }
        });
    }

    return {
        loadTasks,
        addTask,
        editTask,
        deleteTask,
        handleAddTask,
        handleTaskActions
    };
})();

document.addEventListener('DOMContentLoaded', function() {
    taskManager.loadTasks();
    taskManager.handleAddTask();
    taskManager.handleTaskActions();
});
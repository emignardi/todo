// modalManager.js
// Handles the display and hiding of modals for adding, editing, and deleting tasks

const modalManager = (function() {
    const modal = document.querySelector('.task_mark');
    const deleteConfirmModal = document.getElementById('delete_confirm_modal');
    const deleteConfirmOkButton = document.getElementById('delete_confirm_ok');
    const deleteConfirmCancelButton = document.getElementById('delete_confirm_cancel');
    const taskNameInput = document.getElementById('task_name_input');
    const taskCategorySelect = document.getElementById('task_category_modal');
    const taskDeadlineInput = document.getElementById('task_deadline');
    const submitButton = document.querySelector('.submit_btn');
    const cancelButton = document.querySelector('.cancel_btn');

    let isEditMode = false;
    let editingIndex = null;
    let deleteIndex = null;

    // Show Add Task Modal
    function showAddTaskModal() {
        isEditMode = false;
        editingIndex = null;
        clearModalInputs();
        modal.style.display = 'flex';
    }

    // Show Edit Task Modal
    function showEditTaskModal(index, task) {
        isEditMode = true;
        editingIndex = index;
        taskNameInput.value = task.name;
        taskCategorySelect.value = task.category;
        taskDeadlineInput.value = task.deadline;
        modal.style.display = 'flex';
    }

    // Hide Modal
    function hideModal() {
        modal.style.display = 'none';
        clearModalInputs();
    }

    // Clear modal input fields
    function clearModalInputs() {
        taskNameInput.value = '';
        taskCategorySelect.value = '';
        taskDeadlineInput.value = '';
    }

    // Handle Submit Button Click
    function handleSubmit() {
        if (taskNameInput.value.trim() === '' || taskCategorySelect.value.trim() === '' || taskDeadlineInput.value.trim() === '') {
            alert('Please fill in all fields before submitting.');
            return;
        }

        const newTask = {
            name: taskNameInput.value,
            category: taskCategorySelect.value,
            deadline: taskDeadlineInput.value
        };

        if (isEditMode) {
            taskManager.editTask(editingIndex, newTask);
        } else {
            taskManager.addTask(newTask.name, newTask.category, newTask.deadline);
        }

        hideModal();
    }

    // Handle Cancel Button Click
    function handleCancel() {
        hideModal();
    }

    // Show Delete Confirmation Modal
    function showDeleteConfirmModal(index) {
        deleteIndex = index;
        deleteConfirmModal.style.display = 'flex';
    }

    // Hide Delete Confirmation Modal
    function hideDeleteConfirmModal() {
        deleteConfirmModal.style.display = 'none';
        deleteIndex = null;
    }

    // Handle Delete Task Confirmation
    function handleDelete(index) {
        showDeleteConfirmModal(index);
    }

    // Confirm Delete Action
    if (deleteConfirmOkButton) {
        deleteConfirmOkButton.addEventListener('click', function() {
            if (deleteIndex !== null) {
                // Make sure that the deleteIndex is valid and points to an existing task
                taskManager.deleteTask(deleteIndex);
                hideDeleteConfirmModal();
            }
        });
    }

    // Cancel Delete Action
    if (deleteConfirmCancelButton) {
        deleteConfirmCancelButton.addEventListener('click', function() {
            hideDeleteConfirmModal();
        });
    }

    // Event Listeners
    if (submitButton) {
        submitButton.addEventListener('click', handleSubmit);
    }

    if (cancelButton) {
        cancelButton.addEventListener('click', handleCancel);
    }

    return {
        showAddTaskModal,
        showEditTaskModal,
        handleDelete
    };
})();

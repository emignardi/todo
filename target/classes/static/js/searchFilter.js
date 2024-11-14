// searchFilter.js
// Handles the searching and filtering of tasks based on user input

const searchFilter = (function() {
    const searchButton = document.getElementById('search_btn');
    const taskNameInput = document.getElementById('task_name');
    const categoryInput = document.getElementById('task_category');
    const deadlineInput = document.getElementById('deadline');
    const sortInput = document.getElementById('sort');
    const showAllButton = document.getElementById('show_all_btn');
    const taskTableBody = document.querySelector('.tab_body');

    // Function to filter tasks based on search criteria
    function filterTasks() {
        const searchValue = taskNameInput.value.trim().toLowerCase();
        const categoryValue = categoryInput.value;
        const deadlineValue = deadlineInput.value;
        const rows = Array.from(taskTableBody.querySelectorAll('tr'));

        // Reset all rows to be visible before filtering
        rows.forEach(row => {
            row.style.display = '';
        });

        // Filter rows based on the search criteria
        rows.forEach(row => {
            const taskName = row.children[0].textContent.toLowerCase();
            const taskCategory = row.children[1].textContent;
            const taskDeadline = row.children[2].textContent;

            const matchesName = searchValue === '' || taskName.includes(searchValue);
            const matchesCategory = categoryValue === '' || taskCategory === categoryValue;
            const matchesDeadline = deadlineValue === '' || taskDeadline === deadlineValue;

            if (!matchesName || !matchesCategory || !matchesDeadline) {
                row.style.display = 'none';
            }
        });

        // Clear search and filter inputs after filtering
        clearInputs();
    }

    // Function to sort tasks based on date
    function sortTasks() {
        const sortValue = sortInput.value;
        let rows = Array.from(taskTableBody.querySelectorAll('tr'));

        // Convert rows to array for sorting
        if (sortValue === '1') { // Ascending Order
            rows.sort((a, b) => new Date(a.children[2].textContent) - new Date(b.children[2].textContent));
        } else if (sortValue === '2') { // Descending Order
            rows.sort((a, b) => new Date(b.children[2].textContent) - new Date(a.children[2].textContent));
        }

        // Remove existing rows and append sorted rows
        taskTableBody.innerHTML = '';
        rows.forEach(row => {
            taskTableBody.appendChild(row);
        });

        // Clear sort input after sorting
        sortInput.value = '';
    }

    // Function to clear search and filter inputs
    function clearInputs() {
        taskNameInput.value = '';
        categoryInput.value = '';
        deadlineInput.value = '';
    }

    // Function to show all tasks (reset any filters)
    function showAllTasks() {
        const rows = Array.from(taskTableBody.querySelectorAll('tr'));
        rows.forEach(row => {
            row.style.display = '';
        });

        // Clear all inputs
        clearInputs();
        sortInput.value = '';
    }

    // Event Listener for Search Button
    if (searchButton) {
        searchButton.addEventListener('click', function(event) {
            event.preventDefault();
            filterTasks();
        });
    }

    // Event Listener for Category Select (Filter by Category)
    if (categoryInput) {
        categoryInput.addEventListener('change', function() {
            filterTasks();
        });
    }

    // Event Listener for Deadline Input (Filter by Deadline)
    if (deadlineInput) {
        deadlineInput.addEventListener('change', function() {
            filterTasks();
        });
    }

    // Event Listener for Sort Select (Sort by Date)
    if (sortInput) {
        sortInput.addEventListener('change', function() {
            sortTasks();
        });
    }

    // Event Listener for Show All Button
    if (showAllButton) {
        showAllButton.addEventListener('click', function(event) {
            event.preventDefault();
            showAllTasks();
        });
    }

    return {
        filterTasks,
        sortTasks,
        showAllTasks
    };
})();

// Trigger search filter when DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    searchFilter.filterTasks();
});

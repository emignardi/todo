// utils.js
// Utility functions to be used across multiple JavaScript modules
const utils = (function() {
    return {
        // Function to clear all input fields
        clearInputs: function(inputs) {
            inputs.forEach(input => {
                input.value = '';
            });
        }
    };
})();

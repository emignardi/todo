// userManager.js
// User management for handling registration and login
const userManager = (function() {
    const passwordStrengthRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

    function validateRegistration(email, username, password, confirmPassword) {
        if (!email || !username || !password || !confirmPassword) {
            return 'Complete all fields.';
        }
    
        if (!passwordStrengthRegex.test(password)) {
            return 'Password must be at least 8 characters long, including at least one uppercase letter, one lowercase letter, and one number.';
        }
    
        if (password !== confirmPassword) {
            return 'Passwords do not match. Please confirm your password.';
        }
    
        return null; // No errors
    }

    function handleRegistration() {
        const registerButton = document.querySelector('.register_btn');
        if (registerButton) {
            registerButton.addEventListener('click', function(event) {
                event.preventDefault();
                const email = document.getElementById('email').value.trim();
                const username = document.getElementById('username').value.trim();
                const password = document.getElementById('password').value;
                const confirmPassword = document.getElementById('confirm_password').value;

                const validationError = validateRegistration(email, username, password, confirmPassword);
                if (validationError) {
                    alert(validationError);
                    return;
                }

                alert('Registration successful!');
                // Clear inputs and redirect to login page
                utils.clearInputs([document.getElementById('email'), document.getElementById('username'), document.getElementById('password'), document.getElementById('confirm_password')]);
                window.location.href = './custom-create-task.html'
            });
        }
    }

    // function handleLogin() {
    //     const loginButton = document.querySelector('.login_btn');
    //     if (loginButton) {
    //         loginButton.addEventListener('click', function(event) {
    //             event.preventDefault();
    //             const username = document.getElementById('username').value.trim();
    //             const password = document.getElementById('password').value;
    //
    //             if (!username || !password) {
    //                 alert('Please fill in all fields.');
    //                 return;
    //             }
    //
    //             // Dummy authentication for demonstration purposes
    //             if (username === 'testuser' && password === 'Password123') {
    //                 alert('Login successful!');
    //                 window.location.href = './index.html';
    //             } else {
    //                 alert('Invalid username or password. Please try again.');
    //             }
    //         });
    //     }
    // }

    

    return {
        handleRegistration,
        // handleLogin
    };
})();

document.addEventListener('DOMContentLoaded', function() {
    userManager.handleRegistration();
    // userManager.handleLogin();
});

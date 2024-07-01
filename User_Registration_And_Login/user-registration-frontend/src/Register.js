import React, { useState } from 'react';

function Register() {
  const [username, setUsername] = useState('');  // State for username
  const [password, setPassword] = useState('');  // State for password

  const handleSubmit = async (e) => {
    e.preventDefault();  // Prevent form submission default behavior
    const response = await fetch('http://localhost:8080/api/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),  // Send username and password in the request body
    });
    if (response.ok) {
      // Handle successful registration
      console.log('Registration successful');
    } else {
      // Handle registration failure
      console.log('Registration failed');
    }
  };

  return (
    <div>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input type="username" value={username} onChange={(e) => setUsername(e.target.value)} />  {/* Update username state */}
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />  {/* Update password state */}
        </div>
        <div>
          <button type="submit">Register</button>  {/* Trigger form submission */}
        </div>
      </form>
    </div>
  );
}

export default Register;
import React, { useState } from 'react';

function Login() {
  const [username, setUsername] = useState('');  // State for username
  const [password, setPassword] = useState('');  // State for password

  const handleSubmit = async (e) => {
    e.preventDefault();  // Prevent form submission default behavior
    const response = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),  // Send username and password in the request body
    });
    if (response.ok) {
      // Handle successful login
      console.log('Login successful');
    } else {
      // Handle login failure
      console.log('Login failed');
    }
  };

  return (
    <div>
      <h1>Login</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Username:</label>
          <input type="username" value={username} onChange={(e) => setUsername(e.target.value)} />  {/* Update username state */ }
        </div>
        <div>
          <label>Password:</label>
          <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />  {/* Update password state */ }
        </div>
        <div>
          <button type="submit">Login</button>  {/* Trigger form submission */ }
        </div>
      </form>
    </div>
  );
}

export default Login;
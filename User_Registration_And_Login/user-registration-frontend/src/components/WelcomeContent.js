import React from 'react'

export default class WelcomeContent extends React.Component {
    render() {
        return (
            <div className='welcome-content text-center'>
                <h1>Welcome to the site!</h1>
                <p>Login to see the secure content</p>
            </div>
        );
    };
}
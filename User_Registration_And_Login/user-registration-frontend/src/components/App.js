import logo from '../logo.svg';
import './App.css';
import AppContent from './AppContent';

function App() {
  return (
    <div>
      <header className="App-header d-flex align-items-center justify-content-center">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          This is a JWT Authentication Site Demo
        </p>
      </header>
      <div>
        <AppContent />
      </div>
    </div>
  );
}

export default App;

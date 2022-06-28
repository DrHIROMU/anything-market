import './App.scss';
import { Link } from 'react-router-dom';

function App() {
  return (
    <div className='App'>
      <h1>Home</h1>
      <nav
        style={{
          borderBottom: "solid 1px",
          paddingBottom: "1rem",
        }}
      >
        <Link to="/market">Market</Link> |{" "}
        <Link to="/upload-file">Upload File</Link>
      </nav>
    </div>
  );
}

export default App;

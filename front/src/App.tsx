import { useState } from "react";

function App() {
  const [state, setState] = useState(0);

  const handleButtonClick = () => {
    setState(state + 1);
  };

  return (
    <>
      <div style={{ width: "100%", display: "flex", justifyContent: "center" }}>
        <button onClick={handleButtonClick} style={{ width: "300px" }}>
          {state}
        </button>
      </div>
    </>
  );
}

export default App;

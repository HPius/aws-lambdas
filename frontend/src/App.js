import React, { Component } from 'react';
import logo from './lambda.png';
import './App.css';


class App extends Component {
  render() {
    return (
      <div className="App">
        <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to Lambda Picture Farm</h2>
        </div>
        <p className="App-intro">
          Please Upload your Picture:
        </p>
          <form ref='uploadForm'
                target="hidden-form"
                id='uploadForm'
                action='http://localhost:3001/upload'
                method='post'
                encType="multipart/form-data">
              <input type="file" name="sampleFile" />
              <input type='submit' value='Do it!' />
          </form>

          <iframe title='hidden' style={{display:'none'}} name="hidden-form"></iframe>
      </div>
    );
  }
}

export default App;

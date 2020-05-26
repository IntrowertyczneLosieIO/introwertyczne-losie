import React from 'react';
import './App.css';
import Container from "react-bootstrap/Container";
import CentralNavbar from "./components/dashboard/CentralNavbar";
import {BrowserRouter, Route} from "react-router-dom";
import SignIn from "./components/auth/SignIn";
import Registration from "./components/auth/Registration"
import LoggedInView from "./components/LoggedInView";
import ChangePassword from "./components/auth/ChangePassword";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            objects: []
        }
    }

    addNewObject(objectsSetName, displayName, expandData, addNewObject, key) {
        return {
            objectsSetName,
            displayName,
            expandData,
            addNewObject,
            key
        }
    }

    componentDidMount() {
        let currentObjects = this.state.objects;
        this.setState({
            objects: [...currentObjects,
                this.addNewObject("Majors", "Kierunki", "Więcej kierunków...", "Dodaj kierunek", 1),
                this.addNewObject("Rooms", "Sale", "Więcej sal...", "Dodaj salę", 2),
                this.addNewObject("Exams", "Egzaminy", "Więcej egzaminów...", "Dodaj egzamin", 3)
            ]
        })
    }

    render() {
        return (
            <BrowserRouter>
                <div className="App bg-light">
                    <Container fluid className={"main-container"}>
                        <CentralNavbar/>
                        <Route exact path={"/changePassword"} component={ChangePassword}/>
                        <Route exact path={"/registration"} component={Registration}/>
                        <Route exact path={"/login"} component={SignIn}/>
                        <Route exact path={"/"} component={
                            (props) => <LoggedInView objects={this.state.objects} {...props} />}/>

                    </Container>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;

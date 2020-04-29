import React from 'react';
import './App.css';
import Container from "react-bootstrap/Container";
import CentralNavbar from "./components/dashboard/CentralNavbar";
import Row from "react-bootstrap/Row";
import Sidenav from "./components/dashboard/Sidenav";
import Col from "react-bootstrap/Col";
import {BrowserRouter, Route} from "react-router-dom";
import Dashboard from "./components/dashboard/Dashboard";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            objects: []
        }
    }

    addNewObject(objectsSetName, expandData, addNewObject, key) {
        return {
            objectsSetName,
            expandData,
            addNewObject,
            key
        }
    }

    componentDidMount() {
        let currentObjects = this.state.objects;
        this.setState({
            objects: [...currentObjects,
                this.addNewObject("Kierunki", "Więcej kierunków...", "Dodaj kierunek", 1),
                this.addNewObject("Sale", "Więcej sal...", "Dodaj salę", 2),
                this.addNewObject("Egzaminy", "Więcej egzaminów...", "Dodaj egzamin", 3)
            ]
        })
    }

    render() {
        return (
            <BrowserRouter>
                <div className="App">
                    <Container fluid>
                        <CentralNavbar/>
                        <Row className={"no-gutters"}>
                            <Col xs={2} className={"custom-sidenav-bg rounded"}>
                                <Sidenav objects={this.state.objects}/>
                            </Col>
                            <Col xs={10}>
                                <Route path={"/"} component={(props) => <Dashboard objects={this.state.objects} {...props}/>}/>
                            </Col>
                        </Row>
                    </Container>
                </div>
            </BrowserRouter>
        );
    }
}

export default App;

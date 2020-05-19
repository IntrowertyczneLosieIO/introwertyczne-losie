import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Col from "react-bootstrap/Col";
import AdminLinks from "./AdminLinks";
import logo from './agh-logo.jpg';

class CentralNavbar extends React.Component {
    render() {
        return (
            <Navbar expand={"lg"} variant={"dark"} className={'navbar-style pt-0 pb-0'}>
                <Navbar.Brand href={"#"} className={"pt-0 pb-0"}>
                    <img src={logo} alt="Logo" style={{height: '9vh'}}/>
                </Navbar.Brand>
                <Nav className={"mr-auto text-light"}>
                    <Col>
                        AGH
                        <h5>Centrum Rekrutacji</h5>
                    </Col>
                </Nav>
                <Nav>
                    <AdminLinks/>
                </Nav>
            </Navbar>
        );
    }
}

export default CentralNavbar;

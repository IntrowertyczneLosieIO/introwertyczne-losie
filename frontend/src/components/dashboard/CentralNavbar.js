import React from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import Col from "react-bootstrap/Col";
import AdminLinks from "./AdminLinks";

class CentralNavbar extends React.Component {
    render() {
        return (
            <Navbar expand={"lg"} variant={"dark"} className={'mb-3 navbar-style rounded'}>
                <Navbar.Brand href={"#"}>
                    LOGO
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
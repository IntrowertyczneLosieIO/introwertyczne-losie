import React from "react";
import Col from "react-bootstrap/Col";
import Sidenav from "./dashboard/Sidenav";
import {Route} from "react-router-dom";
import Dashboard from "./dashboard/Dashboard";
import Row from "react-bootstrap/Row";

class LoggedInView extends React.Component {
    render() {
        return (
            <Row className={"no-gutters"}>
                <Col xs={2} className={"custom-sidenav-bg rounded"}>
                    <Sidenav objects={this.props.objects}/>
                </Col>
                <Col xs={10}>
                    <Route path={"/"} component={(props) => <Dashboard objects={this.props.objects} {...props}/>}/>
                </Col>
            </Row>
        );
    }
}

export default LoggedInView;
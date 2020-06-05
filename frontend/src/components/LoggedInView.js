import React from "react";
import Sidenav from "./dashboard/Sidenav";
import {Route} from "react-router-dom";
import Dashboard from "./dashboard/Dashboard";
import Row from "react-bootstrap/Row";

class LoggedInView extends React.Component {
    render() {
        return (
            <Row className={"no-gutters grid-container"}>
                <div className={"custom-sidenav-bg rounded sidebar"}>
                    <Sidenav objects={this.props.objects}/>
                </div>
                <div className={"main-content"}>
                    <Route path={"/"} component={(props) => <Dashboard objects={this.props.objects} {...props}/>}/>
                </div>
            </Row>
        );
    }
}

export default LoggedInView;
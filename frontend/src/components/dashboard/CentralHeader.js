import React from "react";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";

class CentralHeader extends React.Component {
    render() {
        return (
            <Row>
                <Col xs={12} className={"mt-1"}>
                    <small>{this.props.versionNumber}</small>
                </Col>
                <Col xs={12}>
                    <h1 className={"text-center mt-4 mb-4"}>{this.props.pageTitle}</h1>
                    <h4 className={"text-center"}>{this.props.subTitle}</h4>
                </Col>
            </Row>
        );
    }
}

export default CentralHeader;
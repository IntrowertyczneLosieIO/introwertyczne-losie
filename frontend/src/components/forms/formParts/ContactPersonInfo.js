import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class ContactPersonInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        return (
          <>
              <h4 className={"mt-3 text-center"}>Osoba kontaktowa {this.props.order}</h4>
              <Form.Row>
                  <FormGroup as={Col} controlId={"name"+this.props.order}>
                      <Form.Label>Imię</Form.Label>
                      <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                  </FormGroup>
                  <FormGroup as={Col} controlId={"surname"+this.props.order}>
                      <Form.Label>Nazwisko</Form.Label>
                      <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                  </FormGroup>
              </Form.Row>
              <Form.Row>
                  <FormGroup as={Col} controlId={"email"+this.props.order}>
                      <Form.Label>e-mail</Form.Label>
                      <Form.Control type={"email"} onChange={this.handleControlChange} required/>
                  </FormGroup>
                  <FormGroup as={Col} controlId={"phone"+this.props.order}>
                      <Form.Label>Telefon</Form.Label>
                      <Form.Control type={"text"} pattern="[0-9]*" onChange={this.handleControlChange} required/>
                  </FormGroup>
              </Form.Row>
          </>
        );
    }
}

export default ContactPersonInfo;
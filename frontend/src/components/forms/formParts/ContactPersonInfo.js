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
              <h5 className={"mt-4 text-secondary mb-3"}>Osoba kontaktowa nr {this.props.order}</h5>
              <Form.Row>
                  <FormGroup as={Col} controlId={"name"+this.props.order}>
                      <Form.Label>Imię</Form.Label>
                      <Form.Control type={"text"} onChange={this.handleControlChange} required={this.props.required} value={this.props.inputValuesFromState["name"+this.props.order]}/>
                  </FormGroup>
                  <FormGroup as={Col} controlId={"surname"+this.props.order}>
                      <Form.Label>Nazwisko</Form.Label>
                      <Form.Control type={"text"} onChange={this.handleControlChange} required={this.props.required} value={this.props.inputValuesFromState["surname"+this.props.order]}/>
                  </FormGroup>
              </Form.Row>
              <Form.Row>
                  <FormGroup as={Col} controlId={"email"+this.props.order}>
                      <Form.Label>E-mail</Form.Label>
                      <Form.Control type={"email"} pattern="^([a-zA-Z0-9_\-\.]+)@(agh)\.(edu)\.(pl)" onChange={this.handleControlChange} required={this.props.required} value={this.props.inputValuesFromState["email"+this.props.order]}/>
                  </FormGroup>
                  <FormGroup as={Col} controlId={"phone"+this.props.order}>
                      <Form.Label>Telefon</Form.Label>
                      <Form.Control type={"text"} pattern="[0-9]*" onChange={this.handleControlChange} value={this.props.inputValuesFromState["phone"+this.props.order]}/>
                  </FormGroup>
              </Form.Row>
          </>
        );
    }
}

export default ContactPersonInfo;

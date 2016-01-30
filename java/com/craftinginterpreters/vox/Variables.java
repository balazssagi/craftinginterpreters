package com.craftinginterpreters.vox;

class Variables {
  private final Variables previous;
  private final String name;
  private Object value;

  Variables(Variables previous, String name, Object value) {
    this.previous = previous;
    this.name = name;
    this.value = value;
  }

  Variables define(String name, Object value) {
    // TODO: Error if already defined.
    return new Variables(this, name, value);
  }

  Object lookUp(Token name) {
    if (this.name.equals(name.text)) return value;

    if (previous == null) {
      throw new RuntimeError("Variable '" + name.text + "' is not defined.",
          name);
    }

    return previous.lookUp(name);
  }

  void assign(Token name, Object value) {
    if (this.name.equals(name.text)) {
      this.value = value;
    } else if (previous == null) {
      throw new RuntimeError("Variable '" + name.text + "' is not defined.",
          name);
    } else {
      previous.assign(name, value);
    }
  }

}
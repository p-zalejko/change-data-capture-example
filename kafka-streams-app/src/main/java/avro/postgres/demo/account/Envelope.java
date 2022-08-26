/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package postgres.demo.account;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class Envelope extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -8633045592136509502L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Envelope\",\"namespace\":\"postgres.demo.account\",\"fields\":[{\"name\":\"before\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"Value\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"long\",\"connect.default\":0},\"default\":0},{\"name\":\"balance\",\"type\":[\"null\",{\"type\":\"bytes\",\"scale\":2,\"precision\":38,\"connect.version\":1,\"connect.parameters\":{\"scale\":\"2\",\"connect.decimal.precision\":\"38\"},\"connect.name\":\"org.apache.kafka.connect.data.Decimal\",\"logicalType\":\"decimal\"}],\"default\":null},{\"name\":\"account_owner_id\",\"type\":\"long\"}],\"connect.name\":\"postgres.demo.account.Value\"}],\"default\":null},{\"name\":\"after\",\"type\":[\"null\",\"Value\"],\"default\":null},{\"name\":\"source\",\"type\":{\"type\":\"record\",\"name\":\"Source\",\"namespace\":\"io.debezium.connector.postgresql\",\"fields\":[{\"name\":\"version\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"connector\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ts_ms\",\"type\":\"long\"},{\"name\":\"snapshot\",\"type\":[{\"type\":\"string\",\"avro.java.string\":\"String\",\"connect.version\":1,\"connect.parameters\":{\"allowed\":\"true,last,false,incremental\"},\"connect.default\":\"false\",\"connect.name\":\"io.debezium.data.Enum\"},\"null\"],\"default\":\"false\"},{\"name\":\"db\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"sequence\",\"type\":[\"null\",{\"type\":\"string\",\"avro.java.string\":\"String\"}],\"default\":null},{\"name\":\"schema\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"table\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"txId\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"lsn\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"xmin\",\"type\":[\"null\",\"long\"],\"default\":null}],\"connect.name\":\"io.debezium.connector.postgresql.Source\"}},{\"name\":\"op\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"ts_ms\",\"type\":[\"null\",\"long\"],\"default\":null},{\"name\":\"transaction\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"ConnectDefault\",\"namespace\":\"io.confluent.connect.avro\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"total_order\",\"type\":\"long\"},{\"name\":\"data_collection_order\",\"type\":\"long\"}]}],\"default\":null}],\"connect.name\":\"postgres.demo.account.Envelope\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.DecimalConversion());
  }

  private static final BinaryMessageEncoder<Envelope> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Envelope> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Envelope> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Envelope> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Envelope> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Envelope to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Envelope from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Envelope instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Envelope fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private postgres.demo.account.Value before;
  private postgres.demo.account.Value after;
  private io.debezium.connector.postgresql.Source source;
  private java.lang.String op;
  private java.lang.Long ts_ms;
  private io.confluent.connect.avro.ConnectDefault transaction;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Envelope() {}

  /**
   * All-args constructor.
   * @param before The new value for before
   * @param after The new value for after
   * @param source The new value for source
   * @param op The new value for op
   * @param ts_ms The new value for ts_ms
   * @param transaction The new value for transaction
   */
  public Envelope(postgres.demo.account.Value before, postgres.demo.account.Value after, io.debezium.connector.postgresql.Source source, java.lang.String op, java.lang.Long ts_ms, io.confluent.connect.avro.ConnectDefault transaction) {
    this.before = before;
    this.after = after;
    this.source = source;
    this.op = op;
    this.ts_ms = ts_ms;
    this.transaction = transaction;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return before;
    case 1: return after;
    case 2: return source;
    case 3: return op;
    case 4: return ts_ms;
    case 5: return transaction;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: before = (postgres.demo.account.Value)value$; break;
    case 1: after = (postgres.demo.account.Value)value$; break;
    case 2: source = (io.debezium.connector.postgresql.Source)value$; break;
    case 3: op = value$ != null ? value$.toString() : null; break;
    case 4: ts_ms = (java.lang.Long)value$; break;
    case 5: transaction = (io.confluent.connect.avro.ConnectDefault)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'before' field.
   * @return The value of the 'before' field.
   */
  public postgres.demo.account.Value getBefore() {
    return before;
  }



  /**
   * Gets the value of the 'after' field.
   * @return The value of the 'after' field.
   */
  public postgres.demo.account.Value getAfter() {
    return after;
  }



  /**
   * Gets the value of the 'source' field.
   * @return The value of the 'source' field.
   */
  public io.debezium.connector.postgresql.Source getSource() {
    return source;
  }



  /**
   * Gets the value of the 'op' field.
   * @return The value of the 'op' field.
   */
  public java.lang.String getOp() {
    return op;
  }



  /**
   * Gets the value of the 'ts_ms' field.
   * @return The value of the 'ts_ms' field.
   */
  public java.lang.Long getTsMs() {
    return ts_ms;
  }



  /**
   * Gets the value of the 'transaction' field.
   * @return The value of the 'transaction' field.
   */
  public io.confluent.connect.avro.ConnectDefault getTransaction() {
    return transaction;
  }



  /**
   * Creates a new Envelope RecordBuilder.
   * @return A new Envelope RecordBuilder
   */
  public static postgres.demo.account.Envelope.Builder newBuilder() {
    return new postgres.demo.account.Envelope.Builder();
  }

  /**
   * Creates a new Envelope RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Envelope RecordBuilder
   */
  public static postgres.demo.account.Envelope.Builder newBuilder(postgres.demo.account.Envelope.Builder other) {
    if (other == null) {
      return new postgres.demo.account.Envelope.Builder();
    } else {
      return new postgres.demo.account.Envelope.Builder(other);
    }
  }

  /**
   * Creates a new Envelope RecordBuilder by copying an existing Envelope instance.
   * @param other The existing instance to copy.
   * @return A new Envelope RecordBuilder
   */
  public static postgres.demo.account.Envelope.Builder newBuilder(postgres.demo.account.Envelope other) {
    if (other == null) {
      return new postgres.demo.account.Envelope.Builder();
    } else {
      return new postgres.demo.account.Envelope.Builder(other);
    }
  }

  /**
   * RecordBuilder for Envelope instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Envelope>
    implements org.apache.avro.data.RecordBuilder<Envelope> {

    private postgres.demo.account.Value before;
    private postgres.demo.account.Value.Builder beforeBuilder;
    private postgres.demo.account.Value after;
    private postgres.demo.account.Value.Builder afterBuilder;
    private io.debezium.connector.postgresql.Source source;
    private io.debezium.connector.postgresql.Source.Builder sourceBuilder;
    private java.lang.String op;
    private java.lang.Long ts_ms;
    private io.confluent.connect.avro.ConnectDefault transaction;
    private io.confluent.connect.avro.ConnectDefault.Builder transactionBuilder;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(postgres.demo.account.Envelope.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.before)) {
        this.before = data().deepCopy(fields()[0].schema(), other.before);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (other.hasBeforeBuilder()) {
        this.beforeBuilder = postgres.demo.account.Value.newBuilder(other.getBeforeBuilder());
      }
      if (isValidValue(fields()[1], other.after)) {
        this.after = data().deepCopy(fields()[1].schema(), other.after);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (other.hasAfterBuilder()) {
        this.afterBuilder = postgres.demo.account.Value.newBuilder(other.getAfterBuilder());
      }
      if (isValidValue(fields()[2], other.source)) {
        this.source = data().deepCopy(fields()[2].schema(), other.source);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (other.hasSourceBuilder()) {
        this.sourceBuilder = io.debezium.connector.postgresql.Source.newBuilder(other.getSourceBuilder());
      }
      if (isValidValue(fields()[3], other.op)) {
        this.op = data().deepCopy(fields()[3].schema(), other.op);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.ts_ms)) {
        this.ts_ms = data().deepCopy(fields()[4].schema(), other.ts_ms);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.transaction)) {
        this.transaction = data().deepCopy(fields()[5].schema(), other.transaction);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (other.hasTransactionBuilder()) {
        this.transactionBuilder = io.confluent.connect.avro.ConnectDefault.newBuilder(other.getTransactionBuilder());
      }
    }

    /**
     * Creates a Builder by copying an existing Envelope instance
     * @param other The existing instance to copy.
     */
    private Builder(postgres.demo.account.Envelope other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.before)) {
        this.before = data().deepCopy(fields()[0].schema(), other.before);
        fieldSetFlags()[0] = true;
      }
      this.beforeBuilder = null;
      if (isValidValue(fields()[1], other.after)) {
        this.after = data().deepCopy(fields()[1].schema(), other.after);
        fieldSetFlags()[1] = true;
      }
      this.afterBuilder = null;
      if (isValidValue(fields()[2], other.source)) {
        this.source = data().deepCopy(fields()[2].schema(), other.source);
        fieldSetFlags()[2] = true;
      }
      this.sourceBuilder = null;
      if (isValidValue(fields()[3], other.op)) {
        this.op = data().deepCopy(fields()[3].schema(), other.op);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.ts_ms)) {
        this.ts_ms = data().deepCopy(fields()[4].schema(), other.ts_ms);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.transaction)) {
        this.transaction = data().deepCopy(fields()[5].schema(), other.transaction);
        fieldSetFlags()[5] = true;
      }
      this.transactionBuilder = null;
    }

    /**
      * Gets the value of the 'before' field.
      * @return The value.
      */
    public postgres.demo.account.Value getBefore() {
      return before;
    }


    /**
      * Sets the value of the 'before' field.
      * @param value The value of 'before'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setBefore(postgres.demo.account.Value value) {
      validate(fields()[0], value);
      this.beforeBuilder = null;
      this.before = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'before' field has been set.
      * @return True if the 'before' field has been set, false otherwise.
      */
    public boolean hasBefore() {
      return fieldSetFlags()[0];
    }

    /**
     * Gets the Builder instance for the 'before' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public postgres.demo.account.Value.Builder getBeforeBuilder() {
      if (beforeBuilder == null) {
        if (hasBefore()) {
          setBeforeBuilder(postgres.demo.account.Value.newBuilder(before));
        } else {
          setBeforeBuilder(postgres.demo.account.Value.newBuilder());
        }
      }
      return beforeBuilder;
    }

    /**
     * Sets the Builder instance for the 'before' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public postgres.demo.account.Envelope.Builder setBeforeBuilder(postgres.demo.account.Value.Builder value) {
      clearBefore();
      beforeBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'before' field has an active Builder instance
     * @return True if the 'before' field has an active Builder instance
     */
    public boolean hasBeforeBuilder() {
      return beforeBuilder != null;
    }

    /**
      * Clears the value of the 'before' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearBefore() {
      before = null;
      beforeBuilder = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'after' field.
      * @return The value.
      */
    public postgres.demo.account.Value getAfter() {
      return after;
    }


    /**
      * Sets the value of the 'after' field.
      * @param value The value of 'after'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setAfter(postgres.demo.account.Value value) {
      validate(fields()[1], value);
      this.afterBuilder = null;
      this.after = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'after' field has been set.
      * @return True if the 'after' field has been set, false otherwise.
      */
    public boolean hasAfter() {
      return fieldSetFlags()[1];
    }

    /**
     * Gets the Builder instance for the 'after' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public postgres.demo.account.Value.Builder getAfterBuilder() {
      if (afterBuilder == null) {
        if (hasAfter()) {
          setAfterBuilder(postgres.demo.account.Value.newBuilder(after));
        } else {
          setAfterBuilder(postgres.demo.account.Value.newBuilder());
        }
      }
      return afterBuilder;
    }

    /**
     * Sets the Builder instance for the 'after' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public postgres.demo.account.Envelope.Builder setAfterBuilder(postgres.demo.account.Value.Builder value) {
      clearAfter();
      afterBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'after' field has an active Builder instance
     * @return True if the 'after' field has an active Builder instance
     */
    public boolean hasAfterBuilder() {
      return afterBuilder != null;
    }

    /**
      * Clears the value of the 'after' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearAfter() {
      after = null;
      afterBuilder = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'source' field.
      * @return The value.
      */
    public io.debezium.connector.postgresql.Source getSource() {
      return source;
    }


    /**
      * Sets the value of the 'source' field.
      * @param value The value of 'source'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setSource(io.debezium.connector.postgresql.Source value) {
      validate(fields()[2], value);
      this.sourceBuilder = null;
      this.source = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'source' field has been set.
      * @return True if the 'source' field has been set, false otherwise.
      */
    public boolean hasSource() {
      return fieldSetFlags()[2];
    }

    /**
     * Gets the Builder instance for the 'source' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public io.debezium.connector.postgresql.Source.Builder getSourceBuilder() {
      if (sourceBuilder == null) {
        if (hasSource()) {
          setSourceBuilder(io.debezium.connector.postgresql.Source.newBuilder(source));
        } else {
          setSourceBuilder(io.debezium.connector.postgresql.Source.newBuilder());
        }
      }
      return sourceBuilder;
    }

    /**
     * Sets the Builder instance for the 'source' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public postgres.demo.account.Envelope.Builder setSourceBuilder(io.debezium.connector.postgresql.Source.Builder value) {
      clearSource();
      sourceBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'source' field has an active Builder instance
     * @return True if the 'source' field has an active Builder instance
     */
    public boolean hasSourceBuilder() {
      return sourceBuilder != null;
    }

    /**
      * Clears the value of the 'source' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearSource() {
      source = null;
      sourceBuilder = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'op' field.
      * @return The value.
      */
    public java.lang.String getOp() {
      return op;
    }


    /**
      * Sets the value of the 'op' field.
      * @param value The value of 'op'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setOp(java.lang.String value) {
      validate(fields()[3], value);
      this.op = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'op' field has been set.
      * @return True if the 'op' field has been set, false otherwise.
      */
    public boolean hasOp() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'op' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearOp() {
      op = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'ts_ms' field.
      * @return The value.
      */
    public java.lang.Long getTsMs() {
      return ts_ms;
    }


    /**
      * Sets the value of the 'ts_ms' field.
      * @param value The value of 'ts_ms'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setTsMs(java.lang.Long value) {
      validate(fields()[4], value);
      this.ts_ms = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'ts_ms' field has been set.
      * @return True if the 'ts_ms' field has been set, false otherwise.
      */
    public boolean hasTsMs() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'ts_ms' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearTsMs() {
      ts_ms = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'transaction' field.
      * @return The value.
      */
    public io.confluent.connect.avro.ConnectDefault getTransaction() {
      return transaction;
    }


    /**
      * Sets the value of the 'transaction' field.
      * @param value The value of 'transaction'.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder setTransaction(io.confluent.connect.avro.ConnectDefault value) {
      validate(fields()[5], value);
      this.transactionBuilder = null;
      this.transaction = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'transaction' field has been set.
      * @return True if the 'transaction' field has been set, false otherwise.
      */
    public boolean hasTransaction() {
      return fieldSetFlags()[5];
    }

    /**
     * Gets the Builder instance for the 'transaction' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public io.confluent.connect.avro.ConnectDefault.Builder getTransactionBuilder() {
      if (transactionBuilder == null) {
        if (hasTransaction()) {
          setTransactionBuilder(io.confluent.connect.avro.ConnectDefault.newBuilder(transaction));
        } else {
          setTransactionBuilder(io.confluent.connect.avro.ConnectDefault.newBuilder());
        }
      }
      return transactionBuilder;
    }

    /**
     * Sets the Builder instance for the 'transaction' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public postgres.demo.account.Envelope.Builder setTransactionBuilder(io.confluent.connect.avro.ConnectDefault.Builder value) {
      clearTransaction();
      transactionBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'transaction' field has an active Builder instance
     * @return True if the 'transaction' field has an active Builder instance
     */
    public boolean hasTransactionBuilder() {
      return transactionBuilder != null;
    }

    /**
      * Clears the value of the 'transaction' field.
      * @return This builder.
      */
    public postgres.demo.account.Envelope.Builder clearTransaction() {
      transaction = null;
      transactionBuilder = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Envelope build() {
      try {
        Envelope record = new Envelope();
        if (beforeBuilder != null) {
          try {
            record.before = this.beforeBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("before"));
            throw e;
          }
        } else {
          record.before = fieldSetFlags()[0] ? this.before : (postgres.demo.account.Value) defaultValue(fields()[0]);
        }
        if (afterBuilder != null) {
          try {
            record.after = this.afterBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("after"));
            throw e;
          }
        } else {
          record.after = fieldSetFlags()[1] ? this.after : (postgres.demo.account.Value) defaultValue(fields()[1]);
        }
        if (sourceBuilder != null) {
          try {
            record.source = this.sourceBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("source"));
            throw e;
          }
        } else {
          record.source = fieldSetFlags()[2] ? this.source : (io.debezium.connector.postgresql.Source) defaultValue(fields()[2]);
        }
        record.op = fieldSetFlags()[3] ? this.op : (java.lang.String) defaultValue(fields()[3]);
        record.ts_ms = fieldSetFlags()[4] ? this.ts_ms : (java.lang.Long) defaultValue(fields()[4]);
        if (transactionBuilder != null) {
          try {
            record.transaction = this.transactionBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("transaction"));
            throw e;
          }
        } else {
          record.transaction = fieldSetFlags()[5] ? this.transaction : (io.confluent.connect.avro.ConnectDefault) defaultValue(fields()[5]);
        }
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Envelope>
    WRITER$ = (org.apache.avro.io.DatumWriter<Envelope>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Envelope>
    READER$ = (org.apache.avro.io.DatumReader<Envelope>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}









